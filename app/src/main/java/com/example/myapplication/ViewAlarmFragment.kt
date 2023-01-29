package com.example.myapplication

import AlarmReceiver
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentViewAlarmBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ViewAlarmFragment : Fragment() {
    private var _binding: FragmentViewAlarmBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var alarmTime:CharSequence = "test"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewAlarmBinding.inflate(inflater, container, false)

        alarmTime = arguments?.getCharSequence("alarmTime") ?: "test"

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textviewWakeUpTime.text = alarmTime
        binding.buttonCancel.setOnClickListener {
            cancelAlarm()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun cancelAlarm() {
        findNavController().navigate(R.id.action_ViewAlarmFragment_to_SetTimeFragment)

        val alarmIntent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, FLAG_IMMUTABLE)

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)

        Toast.makeText(context, "Alarm cancelled", Toast.LENGTH_SHORT).show()
    }
}