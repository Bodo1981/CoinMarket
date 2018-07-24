package bahl.christian.coinmarketcap.base.mvi

import bahl.christian.coinmarketcap.base.IRef

sealed class ScreenIntent {

  abstract val ref: IRef

  data class InitialLoad(override val ref: IRef) : ScreenIntent()
  data class Refresh(override val ref: IRef) : ScreenIntent()

}