package bahl.christian.coinmarketcap.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import bahl.christian.coinmarketcap.R

abstract class AbsBaseFragment<VS> : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_home, container, false)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    viewStates.observe(this, Observer<VS> { viewState ->
      renderViewState(viewState)
    })
    bindIntents()
  }

  abstract val viewStates: LiveData<VS>
  abstract fun bindIntents()
  abstract fun renderViewState(viewState: VS)
}