package com.example.phonepemoviesapp.model

import com.google.gson.annotations.SerializedName
import java.util.*


data class MovieData (

  @SerializedName("page"          ) var page         : Int?               = null,
  @SerializedName("results"       ) var results      : ArrayList<Results> =     ArrayList(),
  @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
  @SerializedName("total_results" ) var totalResults : Int?               = null

)