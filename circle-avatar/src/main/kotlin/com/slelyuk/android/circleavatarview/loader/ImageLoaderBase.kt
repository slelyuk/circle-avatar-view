package com.slelyuk.android.circleavatarview.loader

import android.support.v7.widget.AppCompatImageView
import com.slelyuk.android.circleavatarview.AvatarPlaceholder

abstract class ImageLoaderBase : IImageLoader {

  override fun loadImage(view: AppCompatImageView, url: String?, name: String) {
    loadImage(view, AvatarPlaceholder(name), url)
  }
}
