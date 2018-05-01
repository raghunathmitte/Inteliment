package au.com.inteliment.poc.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import au.com.inteliment.poc.IntelimentApplication
import au.com.inteliment.poc.model.AppData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class DataRepository @Inject constructor(private var context: IntelimentApplication) {
    private var mutableLiveData = MutableLiveData<List<AppData>>()
    private val TAG = DataRepository::class.java.name

    fun fetchTransportData(fileName: String): MutableLiveData<List<AppData>> {
        try {
            val inputStream = context.assets?.open(fileName)
            val size = inputStream?.available()
            val buffer = ByteArray(size!!)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, Charset.defaultCharset())
            mutableLiveData.value = Gson().fromJson<List<AppData>>(json, object : TypeToken<List<AppData>>() {}.type)
        } catch (ex: IOException) {
            Log.d(TAG, String.format("%s", ex.printStackTrace()))
        }
        return mutableLiveData
    }
}