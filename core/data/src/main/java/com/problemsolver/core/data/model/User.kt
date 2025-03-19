package com.problemsolver.core.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "users")
@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "createdAt")
    @ColumnInfo(name = "createdAt")
    val createdAt: String,
    @Json(name = "name")
    @ColumnInfo(name = "name")
    val name: String,
    @Json(name = "avatar")
    @ColumnInfo(name = "avatar")
    val avatar: String,
    @Json(name = "city")
    @ColumnInfo(name = "city")
    val city: String,
    @Json(name = "country")
    @ColumnInfo(name = "country")
    val country: String,
    @Json(name = "county")
    @ColumnInfo(name = "county")
    val county: String,
    @Json(name = "address_no")
    @ColumnInfo(name = "address_no")
    val addressNo: String,
    @Json(name = "street")
    @ColumnInfo(name = "street")
    val street: String,
    @Json(name = "zip_code")
    @ColumnInfo(name = "zip_code")
    val zipCode: String,
    @Json(name = "id")
    @ColumnInfo(name = "id")
    @PrimaryKey val id: String
)

fun userEmpty() = User(
    createdAt = "",
    name = "",
    avatar = "",
    city = "",
    country = "",
    county = "",
    addressNo = "",
    street = "",
    zipCode = "",
    id = ""
)
