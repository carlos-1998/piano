package com.carlos.pianoapplication.util

import android.content.Context
import com.carlos.pianoapplication.model.Key

class KeyViewFactory(
    private val context: Context?,
    private val backgroundManager: BackgroundManager = BackgroundManager(context?.resources)
) {

    fun createViews(keys: Array<Key>): Array<KeyView> {
        val views = mutableListOf<KeyView>()

        for (key in keys) {
            val view = KeyView(context, key)
            backgroundManager.setViewBackground(view)
            views.add(view)
        }

        return views.toTypedArray()
    }
}