package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentFirstBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SetTimeFragment : Fragment() {

    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textviewCurrentTime.text = getTime(0, 0)

        binding.buttonWakeUp1.text = "${getTime(1, 30)} (1.5 hours)"
        binding.buttonWakeUp2.text = "${getTime(3,0)} (3 hours)"
        binding.buttonWakeUp3.text = "${getTime(4, 30)} (4.5 hours)"
        binding.buttonWakeUp4.text = "${getTime(6, 0)} (6 hours)"
        binding.buttonWakeUp5.text = "${getTime(7, 30)} (7.5 hours)"
        binding.buttonWakeUp6.text = "${getTime(9, 0)} (9 hours)"

        binding.buttonWakeUp1.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            Toast.makeText(context, binding.buttonWakeUp1.text, Toast.LENGTH_SHORT).show()
        }
        binding.buttonWakeUp2.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            Toast.makeText(context, binding.buttonWakeUp2.text, Toast.LENGTH_SHORT).show()
        }
        binding.buttonWakeUp3.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            Toast.makeText(context, binding.buttonWakeUp3.text, Toast.LENGTH_SHORT).show()
        }
        binding.buttonWakeUp4.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            Toast.makeText(context, binding.buttonWakeUp4.text, Toast.LENGTH_SHORT).show()
        }
        binding.buttonWakeUp5.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            Toast.makeText(context, binding.buttonWakeUp5.text, Toast.LENGTH_SHORT).show()
        }

        binding.buttonWakeUp6.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            Toast.makeText(context, binding.buttonWakeUp6.text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NewApi")
    private fun getTime(hoursToAdd: Long, minsToAdd: Long): String? {
        val current = LocalDateTime.now()
        val target = current.plusHours(hoursToAdd).plusMinutes(minsToAdd)

        val formatter = DateTimeFormatter.ofPattern("h:mm a")
        val formatted = target.format(formatter)

        return formatted
    }
}