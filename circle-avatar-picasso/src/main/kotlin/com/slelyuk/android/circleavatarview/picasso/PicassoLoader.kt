package com.slelyuk.android.circleavatarview.picasso

import com.slelyuk.android.circleavatarview.AvatarPlaceholder
import com.slelyuk.android.circleavatarview.loader.ImageLoaderBase
import com.slelyuk.android.circleavatarview.CircleAvatarView
import com.squareup.picasso.Picasso

/**
 * Use *Picasso* to load images into *CircleAvatarView* instance.
 */
class PicassoLoader : ImageLoaderBase() {

  override fun loadImage(view: CircleAvatarView, placeholder: AvatarPlaceholder, url: String?) {
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
