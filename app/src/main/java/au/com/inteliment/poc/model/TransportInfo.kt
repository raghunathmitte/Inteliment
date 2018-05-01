package au.com.inteliment.poc.model

import com.google.gson.annotations.SerializedName

data class TransportInfo(@SerializedName("car") var byCar: String, @SerializedName("train") var byTrain: String)