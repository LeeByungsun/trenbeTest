package com.trenbe.test.ui.main.adapter

/**
 * Created by bslee on 2019-03-10.
 */

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.trenbe.test.MainActivity
import com.trenbe.test.R
import com.trenbe.test.TestApplication
import com.trenbe.test.TestApplication.Companion.density
import com.trenbe.test.network.vo.Categori
import com.trenbe.test.network.vo.Images
import kotlinx.android.synthetic.main.item_common.view.*


class WomanListAdapter(commonData: MutableLiveData<List<Categori>>) :
    RecyclerView.Adapter<WomanListAdapter.CommonViewHolder>() {

    private val TAG = javaClass.simpleName
    private var categoryList: MutableLiveData<List<Categori>>? = commonData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_common, parent, false)
        return CommonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList!!.value?.size ?: 0
    }


    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val categori = categoryList!!.value!![position]
        categori.run {
            holder!!.name.text = name
            setImage(images, holder!!.image)

        }
    }

    class CommonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image = view.image
        var name = view.name
    }

    fun setImage(images: Images, view: ImageView) {
        println("densty = ${density}")
        when {
            density!! <1f -> Picasso.get().load(images._1x).into(view)
            density!! < 2 -> Picasso.get().load(images._2x).into(view)
            else -> Picasso.get().load(images._3x).into(view)
        }

    }
}