package com.dj.dialoglifecycletest

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object{
        const val PERMISSION_REQUEST_READ_CONTACTS=1000
        const val CHANNEL_ID = "channelId"
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
        btn_show_notification.setOnClickListener {
            val title = "Title"
            val description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle(title)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setFullScreenIntent(getFullScreenIntent(), true)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            with(notificationManager) {
                buildChannel()
                val notification = builder.build()
                notify(0, notification)
            }
        }
        btn_show_activity_dialog.setOnClickListener {
            startActivity(Intent(this, DialogActivity::class.java))
        }
        button_full_screen_dialog.setOnClickListener {
            val dialog = FullScreenDialog(this)
            dialog.show()
            val window = dialog.window
            window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        }
    }
    private fun Context.getFullScreenIntent(): PendingIntent {
        val intent = Intent(this, FullScreenActivity::class.java)
        return PendingIntent.getActivity(this, 0, intent, 0)
    }
    private fun NotificationManager.buildChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Test"
            val desc = "test desc"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = desc
            }
            createNotificationChannel(channel)
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