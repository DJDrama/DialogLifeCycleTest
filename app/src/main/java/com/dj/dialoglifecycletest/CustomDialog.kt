package com.dj.dialoglifecycletest

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.dialog_custom.*

class CustomDialog(context: Context): Dialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //cannot cancel unless pressing the confirm button
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        setContentView(R.layout.dialog_custom)
        btn_confirm.setOnClickListener {
            dismiss()
        }
    }

}