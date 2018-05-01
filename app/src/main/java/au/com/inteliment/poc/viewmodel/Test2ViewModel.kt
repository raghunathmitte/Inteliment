package au.com.inteliment.poc.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import au.com.inteliment.poc.IntelimentApplication
import au.com.inteliment.poc.model.AppData
import au.com.inteliment.poc.repository.DataRepository
import javax.inject.Inject

class Test2ViewModel : ViewModel() {

    @Inject
    lateinit var repository: DataRepository

    private var appDataList: MutableLiveData<List<AppData>>? = null
    private var selectedItem: LiveData<AppData> = MutableLiveData<AppData>()
    private var locationName = MutableLiveData<String>()

    init {
        IntelimentApplication.appComponent?.inject(this)
        appDataList = repository.fetchTransportData("mock.json")

        //When a location is set it, transforms the location name input to Transport Information
        selectedItem = Transformations.switchMap(locationName, { locationName ->
            getSelectedItemInfoByLocationName(locationName)
        })
    }

    fun getLocationNames(transportList: List<AppData>?): List<String> {
        val locationNames = arrayListOf<String>()
        transportList?.forEach { locationNames.add(it.name) }
        return locationNames
    }

    fun getAppDataList(): LiveData<List<AppData>>? {
        return appDataList
    }

    fun getSelectedItemInfo(): LiveData<AppData> {
        return selectedItem
    }

    //This function is invoked when user selects from dropdown
    fun setLocationNameSelected(locationName: String) {
        this.locationName.value = locationName
    }

    private fun getSelectedItemInfoByLocationName(locationName: String): LiveData<AppData>? {
        val selectedItem = MutableLiveData<AppData>()
        val indexSelected = appDataList?.value?.indexOfFirst { it.name == locationName }
        if (indexSelected != null && indexSelected >= 0)
            selectedItem.value = this.appDataList?.value?.get(indexSelected)
        return selectedItem
    }
}