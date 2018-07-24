package bahl.christian.coinmarketcap.base.scheduler

import io.reactivex.Scheduler

interface ISchedulerProvider {

  fun ui(): Scheduler

  fun io(): Scheduler

  fun computation(): Scheduler

}