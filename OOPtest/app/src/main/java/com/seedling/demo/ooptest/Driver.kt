package com.seedling.demo.ooptest
import android.util.Log
class Driver(name :String, credit:Int) {
    val driverName=name
    var credits=50
    init {
        credits+=credit
    }
    fun showDetails(){
        Log.i("MyTag","Driver is $driverName and has $credits")
    }
}