package com.slelyuk.android.circleavatarview.picasso

import android.support.v7.widget.AppCompatImageView
import com.slelyuk.android.circleavatarview.AvatarPlaceholder
import com.slelyuk.android.circleavatarview.loader.ImageLoaderBase
import com.squareup.picasso.Picasso

/**
 * Use *Picasso* to load images into *CircleAvatarView* instance.
 */
class PicassoLoader : ImageLoaderBase() {

  override fun loadImage(view: AppCompatImageView, placeholder: AvatarPlaceholder, url: String?) {
    if (url.isNullOrEmpty()) {
      view.setImageDrawable(placeholder)
      return
    }

    Picasso.with(view.context)
        .load(url)
        .placeholder(placeholder)
        .fit()
        .into(view)
  }
}
