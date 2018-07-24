package bahl.christian.coinmarketcap.base.mvi

import bahl.christian.coinmarketcap.base.Screen
import bahl.christian.coinmarketcap.base.mvi.ScreenViewStateAction.ActionContent
import bahl.christian.coinmarketcap.base.mvi.ScreenViewStateAction.ActionError
import bahl.christian.coinmarketcap.base.mvi.ScreenViewStateAction.ActionLoad

data class ScreenViewState(
  val loading: Boolean = true,
  val content: Screen? = null,
  val error: Throwable? = null) {

  fun reduce(action: ScreenViewStateAction): ScreenViewState = when (action) {
    is ActionLoad -> copy(loading = true)
    is ActionContent -> copy(loading = false, content = action.content, error = null)
    is ActionError -> copy(loading = false, error = action.error)
  }
}