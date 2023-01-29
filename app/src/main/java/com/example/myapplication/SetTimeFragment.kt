package com.example.myapplication

import AlarmReceiver
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_MUTABLE
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentSetTimeBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SetTimeFragment : Fragment() {
    private var _binding: FragmentSetTimeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSetTimeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textviewCurrentTime.text = getTime(0, 0)

        binding.buttonWakeUp1.text = getTime(1, 30)
        binding.buttonWakeUp2.text = getTime(3,0)
        binding.buttonWakeUp3.text = getTime(4, 30)
        binding.buttonWakeUp4.text = getTime(6, 0)
        binding.buttonWakeUp5.text = getTime(7, 30)
        binding.buttonWakeUp6.text = getTime(9, 0)

        binding.buttonWakeUp1.setOnClickListener {
            setAlarm(binding.buttonWakeUp1.text,1.5)
        }
        binding.buttonWakeUp2.setOnClickListener {
            setAlarm(binding.buttonWakeUp2.text,3.0)
        }
        binding.buttonWakeUp3.setOnClickListener {
            setAlarm(binding.buttonWakeUp3.text,4.5)
        }
        binding.buttonWakeUp4.setOnClickListener {
            setAlarm(binding.buttonWakeUp4.text,6.0)
        }
        binding.buttonWakeUp5.setOnClickListener {
            setAlarm(binding.buttonWakeUp5.text,7.5)
        }
        binding.buttonWakeUp6.setOnClickListener {
            setAlarm(binding.buttonWakeUp6.text, 9.0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setAlarm(alarmTime: CharSequence, additionalTime: Double) {
        val additionalTimeTimeInMillis = (additionalTime * 60 * 60 * 1000).toLong()
        val alarmTimeMillis = System.currentTimeMillis() + additionalTimeTimeInMillis

        val alarmIntent = Intent(context, AlarmReceiver::class.java)
        alarmIntent.putExtra("alarmTimeMillis", alarmTimeMillis)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, FLAG_IMMUTABLE)

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTimeMillis, pendingIntent)

        Toast.makeText(context, "Alarm set for $additionalTime hours from now", Toast.LENGTH_SHORT).show()

//        TODO setting arguments isn't working right. Maybe related to the navigation to the other fragment?
        val viewAlarmFragment = ViewAlarmFragment()
        val bundle = Bundle()
        bundle.putCharSequence("alarmTime", alarmTime)
        viewAlarmFragment.arguments = bundle

        findNavController().navigate(R.id.action_SetTimeFragment_to_ViewAlarmFragment)
    }

    @SuppressLint("NewApi")
    private fun getTime(hoursToAdd: Long, minsToAdd: Long): String? {
        val current = LocalDateTime.now()
        val target = current.plusHours(hoursToAdd).plusMinutes(minsToAdd)

        val formatter = DateTimeFormatter.ofPattern("h:mm a")
        return target.format(formatter)
    }
}