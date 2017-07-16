package com.slelyuk.android.circleavatarview.loader

import android.support.v7.widget.AppCompatImageView
import com.slelyuk.android.circleavatarview.AvatarPlaceholder

/**
 * Base image loader interface required for a custom loader implementation.
 */
interface IImageLoader {
  /**
   * Load image from given *url* into specified *view*.
   *
   * @param[view] view
   * @param[url] url
   */
  fun loadImage(view: AppCompatImageView, placeholder: AvatarPlaceholder, url: String?)

  /**
   * Load image from given *url* into specified *view*.
   *
   * Use defined *name* as a placeholder if image is not available.
   *
   * @param[view] view
   * @param[url] url
   * @param[name] name
   */
  fun loadImage(view: AppCompatImageView, url: String?, name: String)
}
