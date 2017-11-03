package com.slelyuk.android.circleavatarview.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.slelyuk.android.circleavatarview.AvatarPlaceholder
import com.slelyuk.android.circleavatarview.CircleAvatarView
import com.slelyuk.android.circleavatarview.demo.R.id
import com.slelyuk.android.circleavatarview.demo.R.layout
import com.slelyuk.android.circleavatarview.picasso.PicassoLoader

class MainActivity : AppCompatActivity() {

  val view1 by lazy { findViewById(id.avatar) as CircleAvatarView }
  val view2 by lazy { findViewById(id.avatar2) as CircleAvatarView }
  val view3 by lazy { findViewById(id.avatar3) as CircleAvatarView }
  val view4 by lazy { findViewById(id.avatar4) as CircleAvatarView }
  val view5 by lazy { findViewById(id.avatar5) as CircleAvatarView }
  val view6 by lazy { findViewById(id.image6) as ImageView }

  val button by lazy { findViewById(id.image6) as ImageView }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)


    PicassoLoader().loadImage(view1, AvatarPlaceholder("First Profile"),
        "https://cdn2.iconfinder.com/data/icons/avatar-face/96/avatar49-512.png")

    PicassoLoader().loadImage(view2, AvatarPlaceholder("Second User"),
        "https://cdn2.iconfinder.com/data/icons/avatar-face/96/avatar50-512.png")

    PicassoLoader().loadImage(view5, AvatarPlaceholder("John Smith"),
        "https://cdn2.iconfinder.com/data/icons/avatar-face/96/avatar48-512.png")

    view3.setImageDrawable(AvatarPlaceholder("Third Member"))
    view4.setImageDrawable(AvatarPlaceholder("N..s.Se.."))

    view6.setImageDrawable(AvatarPlaceholder("N. O."))
  }


}
