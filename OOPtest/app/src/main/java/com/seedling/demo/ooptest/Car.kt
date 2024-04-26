package com.seedling.demo.ooptest
import android.util.Log
import kotlin.math.max

open class Car {
    var maxSpeed=30
    open fun start(){
        Log.i("MyTag", "Car Is starting")
        Log.i("MyTag", "maximum speed is ${maxSpeed}")
    }
}