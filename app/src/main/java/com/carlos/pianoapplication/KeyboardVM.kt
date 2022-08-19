package com.carlos.pianoapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carlos.pianoapplication.model.Key
import com.carlos.pianoapplication.util.Keyboard
import com.carlos.pianoapplication.util.KeyboardFactory

class KeyboardVM(
    private val keyboard: Keyboard = KeyboardFactory().createDefaultKeyboard(),
    val liveDataKeys: MutableLiveData<Keyboard> = MutableLiveData()
):ViewModel() {


    init {
        liveDataKeys.postValue(keyboard)
    }

    fun updatePulsedKeys(keys: Array<Key>) {
        val keysToRelease = keyboard.keys.filter { it.pulsed && !keys.contains(it) }
        val keysToPulse = keyboard.keys.filter { !it.pulsed && keys.contains(it) }

        for (key in keysToRelease) keyboard.keys.firstOrNull { it == key }?.pulsed = false
        for (key in keysToPulse) keyboard.keys.firstOrNull { it == key }?.pulsed = true

        liveDataKeys.postValue(keyboard)
    }

    fun releaseKey(key: Key) {
        keyboard.keys.firstOrNull { it == key }?.pulsed = false
        liveDataKeys.postValue(keyboard)
    }

}

