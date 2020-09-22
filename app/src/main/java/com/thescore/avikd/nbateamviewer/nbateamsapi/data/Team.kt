package com.thescore.avikd.nbateamviewer.nbateamsapi.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team (
    val id: Int,
    @SerializedName("full_name")
    val fullName: String,
    val wins: Int,
    val losses: Int,
    val players: List<Player>
): Parcelable