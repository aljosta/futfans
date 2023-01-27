package com.example.futfans

import android.view.View
import android.widget.ImageView
import coil.load
import coil.request.ImageRequest

fun String?.value(default: String = "") = this ?: default

fun <T> List<T>?.value(default: List<T> = emptyList()) = this ?: default

fun Int?.value(default: Int = 0) = this ?: default

fun ImageView.loadSvgFromUrl(image: String, builder: ImageRequest.Builder.() -> Unit = {}) =
    load(image) {
        builder()
    }

fun View.setOnSafeClickListener(onClick: () -> Unit) =
    setOnClickListener {
        apply {
            isEnabled = false
            postDelayed({ isEnabled = true }, 400)
        }
        onClick()
    }
