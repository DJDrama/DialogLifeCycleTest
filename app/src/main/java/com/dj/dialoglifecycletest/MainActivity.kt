package com.dj.dialoglifecycletest

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object{
        const val PERMISSION_REQUEST_READ_CONTACTS=1000
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_show_permission_ui.setOnClickListener {
           val isPermissionGranted = checkSelfPermission(android.Manifest.permission.READ_CONTACTS)
            if(isPermissionGranted != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS),
                    PERMISSION_REQUEST_READ_CONTACTS)
            }
        }
        btn_show_custom_dialog.setOnClickListener {
            CustomDialog(this).show()
        }
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause Called!", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume Called!", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_READ_CONTACTS -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission granted
                } else {
                    //permission denied
                }
                return
            }
        }
    }

}