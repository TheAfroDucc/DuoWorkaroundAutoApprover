package duoworkaround.autoapprover;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

//Service to listen for and respond to notifications
public class NotificationListener extends NotificationListenerService {
    //Overrides necessary for some versions of android
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) { }
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    private static final String TAG = "DWAA";
    int notificationId;
    @Override
    public void onCreate(){
        super.onCreate();
        Context context = getApplicationContext();
        CharSequence text = "Duo listener is active";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        try {
            //Only respond to com.duosecurity.duomobile notifications with at least 1 action
            if (sbn.getPackageName().equals("com.duosecurity.duomobile") && sbn.getNotification().actions != null && sbn.getNotification().actions.length > 0) {
                //Tap the 'Tap to View Actions' button
                if (sbn.getNotification().actions[0].title.equals("Tap To View Actions")) {
                    this.notificationId = sbn.getId();
                    try {
                        sbn.getNotification().actions[0].actionIntent.send();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed to execute 'Tap To View Actions'", Toast.LENGTH_LONG).show();
                        Log.e(TAG, "Failed to execute 'Tap To View Actions'");
                    }
                //Tap the 'Approve' button
                } else if (sbn.getId() == this.notificationId && sbn.getNotification().actions[0].title.equals("Approve")) {
                    this.notificationId = 0;
                    try {
                        sbn.getNotification().actions[0].actionIntent.send();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed to execute 'Approve'", Toast.LENGTH_LONG).show();
                        Log.e(TAG, "Failed to execute 'Approve'");
                    }
                }
            }
        } catch (Exception e){
            //Catch any uncaught/unexpected errors
            Log.e(TAG, "Error: " + e.toString());
        }
    }
}
