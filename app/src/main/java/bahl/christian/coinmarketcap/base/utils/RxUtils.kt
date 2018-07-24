package bahl.christian.coinmarketcap.base.utils

import io.reactivex.Observable

fun <T : Any, U : Any> Observable<T>.notOfType(clazz: Class<U>): Observable<T> {
  checkNotNull(clazz) { "clazz is null" }
  return filter { !clazz.isInstance(it) }
}