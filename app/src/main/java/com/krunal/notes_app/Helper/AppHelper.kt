package com.krunal.notes_app.Helper

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

public fun Date.getDateString(s: String): String? {
    try {
        val sdf = SimpleDateFormat("dd MMM yy")
        val netDate = Date(s.toLong() )
        return sdf.format(netDate)
    } catch (e: Exception) {
        Log.d("msg", "getDateString: "+e.toString())
        return ""
    }
}
public fun Date.getTimeString(s: String): String? {
    try {
        val sdf = SimpleDateFormat("HH:mm a")
        val netDate = Date(s.toLong() )
        return sdf.format(netDate)
    } catch (e: Exception) {
        Log.d("msg", "getTimeString: "+e.toString())
        return ""
    }
}