package duoworkaround.autoapprover;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Autostart extends BroadcastReceiver
{
    public void onReceive(Context context, Intent arg1)
    {
        Intent intent = new Intent(context,NotificationListener.class);
        context.startService(intent);
        Log.i("Autostart", "Started");
    }
}
