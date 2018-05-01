package au.com.inteliment.poc.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.com.inteliment.poc.R
import kotlinx.android.synthetic.main.layout_fragment.*

class MainFragment : Fragment() {
    var name: String? = null
    var fragmentClickCallback: OnFragmentClick? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.layout_fragment, container, false)
        view?.setOnClickListener { fragmentClickCallback?.onFragmentClick(name) }
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        fragmentName.text = name
    }

    override fun onAttach(context: Context?) {
        if (context is OnFragmentClick) {
            fragmentClickCallback = context
        }
        super.onAttach(context)
    }

    companion object {
        //bundle can be passed for more arguments
        fun instance(name: String): MainFragment {
            val fragment = MainFragment()
            fragment.name = name
            return fragment
        }
    }

    interface OnFragmentClick {
        fun onFragmentClick(fragmentName: String?)
    }
}