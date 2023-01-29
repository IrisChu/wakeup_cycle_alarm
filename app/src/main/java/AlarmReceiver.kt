import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.myapplication.MainActivity
import com.example.myapplication.ViewAlarmFragment

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(context, ViewAlarmFragment::class.java).let { intent ->
            PendingIntent.getActivity(context, 0, intent, 0)
        }
        val alarmTime = intent.getLongExtra("alarmTime", 0)

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTime, alarmIntent)
    }
}