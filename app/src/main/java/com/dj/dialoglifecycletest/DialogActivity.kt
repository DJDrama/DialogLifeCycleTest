package com.dj.dialoglifecycletest

import android.app.Activity
import android.os.Bundle

class DialogActivity: Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
    }
}