package bahl.christian.coinmarketcap.home

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import bahl.christian.coinmarketcap.R
import bahl.christian.coinmarketcap.data.Cryptocurrency
import kotlinx.android.synthetic.main.fragment_home.detailsButton
import org.koin.android.architecture.ext.viewModel
import java.util.Random

class HomeFragment : Fragment() {

    // not working
    //private val homeViewModel: HomeViewModel2 by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // working
        val homeViewModel = ViewModelProviders.of(this).get(HomeViewModel2::class.java)

        homeViewModel.cryptocurrencyLiveData.observe(this,
                                                 Observer<List<Cryptocurrency>> {

                                                 })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsButton.setOnClickListener {
            view.findNavController().navigate(R.id.navToDetails)
        }
    }

}