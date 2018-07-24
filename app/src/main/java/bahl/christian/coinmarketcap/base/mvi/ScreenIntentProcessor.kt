package bahl.christian.coinmarketcap.base.mvi

import bahl.christian.coinmarketcap.base.Screen
import bahl.christian.coinmarketcap.base.mvi.ScreenIntent.InitialLoad
import bahl.christian.coinmarketcap.base.mvi.ScreenIntent.Refresh
import bahl.christian.coinmarketcap.base.mvi.ScreenViewStateAction.ActionContent
import bahl.christian.coinmarketcap.base.mvi.ScreenViewStateAction.ActionError
import bahl.christian.coinmarketcap.base.mvi.ScreenViewStateAction.ActionLoad
import bahl.christian.coinmarketcap.base.scheduler.ISchedulerProvider
import bahl.christian.coinmarketcap.data.source.CryptocurrencyRepository
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class ScreenIntentProcessor(
  private val cryptocurrencyRepository: CryptocurrencyRepository,
  private val schedulerProvider: ISchedulerProvider) {

  val intentProcessor = ObservableTransformer<ScreenIntent, ScreenViewStateAction> { intent ->
    intent.publish { shared ->
      Observable.merge(
        shared.ofType(InitialLoad::class.java).compose(loadProcessor(false)),
        shared.ofType(Refresh::class.java).compose(loadProcessor(true)))
    }
  }

  private fun <I : ScreenIntent> loadProcessor(isInitialLoad: Boolean) =
    ObservableTransformer<I, ScreenViewStateAction> { intent ->
      intent.switchMap { switched ->
        val observable: Observable<ScreenViewStateAction> = cryptocurrencyRepository
          .getCryptocurrencies()
          .subscribeOn(schedulerProvider.io())
          .map { ActionContent(switched.ref, Screen()) }
          .cast(ScreenViewStateAction::class.java)
          .onErrorReturn { ActionError(switched.ref, it) }
          .observeOn(schedulerProvider.ui())

        if (isInitialLoad) {
          observable.startWith(ActionLoad(switched.ref))
        }

        observable
      }
    }
}