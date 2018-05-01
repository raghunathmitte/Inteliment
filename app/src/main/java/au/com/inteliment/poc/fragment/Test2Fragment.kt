package au.com.inteliment.poc.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import au.com.inteliment.poc.R
import au.com.inteliment.poc.model.AppData
import au.com.inteliment.poc.viewmodel.Test2ViewModel
import kotlinx.android.synthetic.main.test2_fragment.*

class Test2Fragment : Fragment() {

    lateinit var callback: Callback
    lateinit var viewModel: Test2ViewModel
    private var selectedItem: AppData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity).get(Test2ViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.test2_fragment, container, false)
        viewModel.getAppDataList()?.observe(this, Observer { appData ->
            updateUI(appData)
        })
        viewModel.getSelectedItemInfo().observe(this, Observer { selectedItem ->
            updateTransportInfo(selectedItem)
        })
        return view
    }

    override fun onAttach(context: Context?) {
        if (context is Callback)
            callback = context
        super.onAttach(context)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.setLocationNameSelected(parent?.adapter?.getItem(position).toString())
            }
        }

        buttonNavigate.setOnClickListener {
            callback.onClickNavigate(selectedItem?.location?.latitude!!, selectedItem?.location?.longitude!!)
        }
    }

    private fun updateTransportInfo(selectedItem: AppData?) {
        this.selectedItem = selectedItem
        modeCar.text = getString(R.string.car, selectedItem?.transportInfo?.byCar)
        modeTrain.text = getString(R.string.train, selectedItem?.transportInfo?.byTrain)
        modeCar.visibility = if (!selectedItem?.transportInfo?.byCar.isNullOrEmpty()) View.VISIBLE else View.GONE
        modeTrain.visibility = if (!selectedItem?.transportInfo?.byTrain.isNullOrEmpty()) View.VISIBLE else View.GONE
    }

    private fun updateUI(transportInfoList: List<AppData>?) {
        spinner.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, viewModel.getLocationNames(transportInfoList))
    }

    companion object {
        fun instance(): Test2Fragment {
            return Test2Fragment()
        }
    }

    interface Callback {
        fun onClickNavigate(latitude: Double, longitude: Double)
    }
}