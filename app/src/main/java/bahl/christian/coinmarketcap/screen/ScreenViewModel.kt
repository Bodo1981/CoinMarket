package bahl.christian.coinmarketcap.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import bahl.christian.coinmarketcap.base.mvi.ScreenIntent
import bahl.christian.coinmarketcap.base.mvi.ScreenIntentProcessor
import bahl.christian.coinmarketcap.base.mvi.ScreenViewState
import bahl.christian.coinmarketcap.base.utils.notOfType
import bahl.christian.coinmarketcap.base.viewmodel.IViewModel
import io.reactivex.BackpressureStrategy.BUFFER
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class ScreenViewModel(private val screenIntentProcessor: ScreenIntentProcessor) : ViewModel(),
                                                                                  IViewModel<ScreenViewState, ScreenIntent> {

  private val intentsSubject = PublishSubject.create<ScreenIntent>()
  private val statesFlowable: Flowable<ScreenViewState> = intentsSubject
    .compose(intentFilter)
    .doOnNext { Timber.d("VIEWSTATE (intent) -> $it") }
    .compose(screenIntentProcessor.intentProcessor)
    .scan(ScreenViewState(), ScreenViewState::reduce)
    .replay(1)
    .autoConnect(0)
    .toFlowable(BUFFER)

  override fun bindIntents(intents: Observable<ScreenIntent>) = intents.subscribe(intentsSubject)

  override fun viewState(): LiveData<ScreenViewState> = LiveDataReactiveStreams.fromPublisher(statesFlowable)

  private val intentFilter
    get() = ObservableTransformer<ScreenIntent, ScreenIntent> { intent ->
      intent.publish { shared ->
        Observable.merge(shared.ofType(ScreenIntent.InitialLoad::class.java).take(1),
                         shared.notOfType(ScreenIntent.InitialLoad::class.java))
      }
    }
}