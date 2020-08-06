# DialogLifecycleTest

1) Show Dialog
onPause Not Called

2) Heads-Up Notification
onPause Not Called

3) Dangerous Permission Dialog(Actually it's an UI)
onPause Called

4) DialogActivity (activity using @android:style/Theme.Dialog theme in AndroidManifest) <br />
onPause Called(since startactivity using an intent)

If your app does not have the requested permissions the user will be presented with UI for accepting them. After the user has accepted or rejected the requested permissions you will receive a callback reporting whether the permissions were granted or not. Your activity has to implement ActivityCompat.OnRequestPermissionsResultCallback and the results of permission requests will be delivered to its onRequestPermissionsResult(int, String[], int[]) method.

<b>This method may start an activity allowing the user to choose which permissions to grant and which to reject. Hence, you should be prepared that your activity may be paused and resumed</b>. Further, granting some permissions may require a restart of you application. In such a case, the system will recreate the activity stack before delivering the result to your onRequestPermissionsResult(int, String[], int[]).

https://developer.android.com/reference/android/support/v4/app/ActivityCompat.html#requestPermissions(android.app.Activity,%20java.lang.String[],%20int)
