package bahl.christian.coinmarketcap.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import bahl.christian.coinmarketcap.data.Cryptocurrency
import bahl.christian.coinmarketcap.data.source.CryptocurrencyRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class HomeViewModel(private val cryptocurrencyRepository: CryptocurrencyRepository) : ViewModel() {

    init {
        Log.e("REPOSITORY", "HomeViewModel")
    }

    val cryptocurrencyLiveData = LiveDataReactiveStreams.fromPublisher(startObservingCrpytocurrency())

    private fun startObservingCrpytocurrency(): Flowable<List<Cryptocurrency>> {
        val processor = BehaviorProcessor.create<List<Cryptocurrency>>()

//        cryptocurrencyRepository.getCryptocurrencies()
//                .doOnSubscribe {
//                    Log.e("REPOSITORY", "HomeViewModel -> onSubscribe")
//                }
//                .toFlowable(BackpressureStrategy.LATEST)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .debounce(400, TimeUnit.MILLISECONDS)
//                .subscribe(processor)

        return processor
    }

}