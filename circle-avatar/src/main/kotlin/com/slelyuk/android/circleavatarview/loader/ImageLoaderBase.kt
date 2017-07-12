package com.slelyuk.android.circleavatarview.loader

import com.slelyuk.android.circleavatarview.AvatarPlaceholder
import com.slelyuk.android.circleavatarview.CircleAvatarView

abstract class ImageLoaderBase : IImageLoader {

  override fun loadImage(view: CircleAvatarView, url: String?, name: String) {
    loadImage(view, AvatarPlaceholder(name), url)
  }
}
