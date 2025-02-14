package com.sravani.randomusers.data.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("gender")
    val gender: String,
    @SerializedName("name")
    val name: Name,
    @SerializedName("location")
    val location: Location,
    @SerializedName("picture")
    val picture: Picture,
    @SerializedName("nat")
    val nat : String
    )

data class Name(
    @SerializedName("title")
    val title: String,
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String,
)

data class Location(
    @SerializedName("street") val street: Street,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
    @SerializedName("postcode") val postcode: String,
    @SerializedName("coordinates") val coordinates: Coordinates,
    @SerializedName("timezone") val timezone: Timezone
) {
    fun getFormattedAddress(): String {
        return "${street.number} ${street.name}, $city, $state, $country, $postcode"
    }
}

data class Street(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String
)

data class Coordinates(
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String
)

data class Timezone(
    @SerializedName("offset") val offset: String,
    @SerializedName("description") val description: String
)

data class Picture(
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("thumbnail") val thumbnail: String
)


