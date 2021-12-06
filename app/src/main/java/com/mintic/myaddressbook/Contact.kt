package com.mintic.myaddressbook

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    val name: String,
    val description: String,
    val points: String,
    val image: String
) :Parcelable
