package bahl.christian.coinmarketcap

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import bahl.christian.coinmarketcap.home.HomeViewModel
import org.koin.android.architecture.ext.viewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}