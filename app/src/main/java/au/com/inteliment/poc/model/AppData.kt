package au.com.inteliment.poc.model

import com.google.gson.annotations.SerializedName


data class AppData(var id: Int,
                   var name: String,
                   @SerializedName("fromcentral") var transportInfo: TransportInfo,
                   var location: Location)