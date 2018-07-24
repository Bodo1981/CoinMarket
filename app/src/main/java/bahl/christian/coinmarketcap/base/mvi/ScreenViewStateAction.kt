package bahl.christian.coinmarketcap.base.mvi

import bahl.christian.coinmarketcap.base.IRef
import bahl.christian.coinmarketcap.base.Screen

sealed class ScreenViewStateAction {

  abstract val ref: IRef

  data class ActionLoad(override val ref: IRef) : ScreenViewStateAction()
  data class ActionContent(override val ref: IRef, val content: Screen) : ScreenViewStateAction()
  data class ActionError(override val ref: IRef, val error: Throwable) : ScreenViewStateAction()
}