package com.carlos.pianoapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.carlos.pianoapplication.KeyboardVM
import com.carlos.pianoapplication.util.Keyboard

class KeyboardFragment : Fragment() {

    private lateinit var keyboardLayout: KeyboardLayout
    private val viewModel : KeyboardVM by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       keyboardLayout = KeyboardLayout(requireContext())
        return keyboardLayout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeToVM(viewModel)
    }

    private fun subscribeToVM(viewModel:KeyboardVM){
        val keyboardObserver = Observer<Keyboard>{ keyboard ->
            if (keyboard != null){
                addKeyboardToLayout(keyboard)
            }
        }
        viewModel.liveDataKeys.observe(viewLifecycleOwner,keyboardObserver)
    }

    private fun addKeyboardToLayout(keyboard: Keyboard) {
        keyboardLayout.clearKeyViews()
        keyboardLayout.addKeyboard(keyboard)
    }

}