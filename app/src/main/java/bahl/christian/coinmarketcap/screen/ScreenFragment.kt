package bahl.christian.coinmarketcap.screen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import bahl.christian.coinmarketcap.base.IRef
import bahl.christian.coinmarketcap.base.fragment.AbsBaseFragment
import bahl.christian.coinmarketcap.base.mvi.ScreenIntent.InitialLoad
import bahl.christian.coinmarketcap.base.mvi.ScreenIntent.Refresh
import bahl.christian.coinmarketcap.base.mvi.ScreenIntentProcessor
import bahl.christian.coinmarketcap.base.mvi.ScreenViewState
import bahl.christian.coinmarketcap.data.source.CryptocurrencyRepository
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_home.button
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import timber.log.Timber

class ScreenFragment : AbsBaseFragment<ScreenViewState>() {

  companion object {
    val screenModule = module {
      factory { CryptocurrencyRepository(get(), get()) }
      factory { ScreenIntentProcessor(get(), get()) }
      viewModel { ScreenViewModel(get()) }
    }
  }

  val ref: IRef = ScreenRef()
  private val refreshIntent = PublishSubject.create<Refresh>()
  private val screenViewModel by viewModel<ScreenViewModel>()

  override fun bindIntents() {
    screenViewModel.bindIntents(Observable.merge(
      Observable.just(InitialLoad(ref)),
      refreshIntent
    ))
  }

  override val viewStates: LiveData<ScreenViewState> by lazy { screenViewModel.viewState() }

  override fun renderViewState(viewState: ScreenViewState) {
    Timber.d("VIEWSTATE -> $viewState")
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    button.setOnClickListener {
      refreshIntent.onNext(Refresh(ref))
    }
  }

}