package com.dj.dialoglifecycletest

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.dialog_custom_full_screen.*

class FullScreenDialog(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set dialog to full screen
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        setContentView(R.layout.dialog_custom_full_screen)
        btn_confirm.setOnClickListener {
            dismiss()
        }
    }
}