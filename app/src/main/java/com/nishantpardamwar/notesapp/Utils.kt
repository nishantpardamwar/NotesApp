package com.nishantpardamwar.notesapp

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun Long.displayDate(): String {
    val date = Date(this)
    val simpleDateFormat = SimpleDateFormat("d MMM yyyy hh:mm a", Locale.getDefault())
    return simpleDateFormat.format(date)
}