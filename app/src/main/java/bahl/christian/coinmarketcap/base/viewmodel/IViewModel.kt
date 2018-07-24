package bahl.christian.coinmarketcap.base.viewmodel

import androidx.lifecycle.LiveData
import io.reactivex.Observable

interface IViewModel<VS, I> {

  fun bindIntents(intents: Observable<I>)

  fun viewState(): LiveData<VS>

}