package com.seedling.demo.ooptest

import android.util.Log

class MyCar : Car(), SpeedController {
    override fun start(){
        Log.i("MyTag","this is MYCAR string...Brand id is ${getBrandId()}")
    }

    override fun accelerate() {

    }

    override fun decelerate() {

    }
}