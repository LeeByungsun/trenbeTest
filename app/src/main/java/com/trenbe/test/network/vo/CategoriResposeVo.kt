package com.buggyani.riiid.network.vo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoriResposeVo( @SerializedName("categories") var categories: List<Categori>) {

}

data class Categori(
    @SerializedName("name") var name: String,
    @SerializedName("images") var images: Images,
    @SerializedName("childCategories") var childCategories: List<ChildCategory>
) : Serializable

data class ChildCategory(
    @SerializedName("name") var name: String,
    @SerializedName("images") var images: Images
) : Serializable

data class Images(
    @SerializedName("1x") var _1x: String,
    @SerializedName("2x") var _2x: String,
    @SerializedName("3x") var _3x: String
) : Serializable