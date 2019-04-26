package com.trenbe.test

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.trenbe.test.network.vo.Images

object Util {
    fun expand(v: View) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val targetHeight = v.measuredHeight
        val initialHeight = v.height
        val distanceToExpand = targetHeight - initialHeight
        println("targetHeight = $targetHeight initialHeight = $initialHeight distanceToExpand = $distanceToExpand")
        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.layoutParams.height = 1
        v.visibility = View.VISIBLE
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                v.layoutParams.height = if (interpolatedTime == 1f)
                    ViewGroup.LayoutParams.WRAP_CONTENT
                else
                    initialHeight + (distanceToExpand * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        a.duration = (distanceToExpand / 2).toLong()
        v.startAnimation(a)

    }

    fun collapse(v: View) {
        val initialHeight = v.measuredHeight
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                } else {
                    v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        a.duration = (initialHeight/2).toLong()
        v.startAnimation(a)
    }

    fun setImage(images: Images, view: ImageView) {
        println("density = ${TestApplication.density}")
        when {
            TestApplication.density!! < 1f -> Picasso.get().load(images._1x).into(view)
            TestApplication.density!! < 2 -> Picasso.get().load(images._2x).into(view)
            else -> Picasso.get().load(images._3x).into(view)
        }

    }
}