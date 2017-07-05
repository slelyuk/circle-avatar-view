package com.slelyuk.android.circleavatarview.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.slelyuk.android.circleavatarview.AvatarPlaceholder
import com.slelyuk.android.circleavatarview.CircleAvatarView
import com.slelyuk.android.circleavatarview.demo.R.id
import com.slelyuk.android.circleavatarview.demo.R.layout
import com.slelyuk.android.circleavatarview.picasso.PicassoLoader

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)

    val view1: CircleAvatarView = findViewById(id.avatar) as CircleAvatarView
    PicassoLoader().loadImage(view1, AvatarPlaceholder("First Profile"),
        "https://cdn2.iconfinder.com/data/icons/avatar-face/96/avatar49-512.png")

    val view2: CircleAvatarView = findViewById(id.avatar2) as CircleAvatarView
    PicassoLoader().loadImage(view2, AvatarPlaceholder("Second User"),
        "https://cdn2.iconfinder.com/data/icons/avatar-face/96/avatar50-512.png")

    val view3: CircleAvatarView = findViewById(id.avatar3) as CircleAvatarView
    view3.setImageDrawable(AvatarPlaceholder("Third Member"))

    val view4: CircleAvatarView = findViewById(id.avatar4) as CircleAvatarView
    view4.setImageDrawable(AvatarPlaceholder("Next Person"))

    val view5: CircleAvatarView = findViewById(id.avatar5) as CircleAvatarView
    PicassoLoader().loadImage(view5, AvatarPlaceholder("John Smith"),
        "https://cdn2.iconfinder.com/data/icons/avatar-face/96/avatar48-512.png")

    val view6: CircleAvatarView = findViewById(id.avatar6) as CircleAvatarView
    view6.setImageDrawable(AvatarPlaceholder("Six Axis"))
  }
}
