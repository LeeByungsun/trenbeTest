package com.trenbe.test.ui.main.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trenbe.test.R
import com.trenbe.test.Util.collapse
import com.trenbe.test.Util.expand
import com.trenbe.test.Util.setImage
import com.trenbe.test.network.vo.Categori
import com.trenbe.test.network.vo.ChildCategory
import kotlinx.android.synthetic.main.item_common.view.*


class WomanListAdapter(commonData: MutableLiveData<List<Categori>>, ctx: Context) :
    RecyclerView.Adapter<WomanListAdapter.CommonViewHolder>() {
    val context = ctx
    private var categoryList: MutableLiveData<List<Categori>>? = commonData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_common, parent, false)
        return CommonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList!!.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val categori = categoryList!!.value!![position]
        categori.run {
            holder.name.text = name
            setImage(images, holder.image)

            if (childCategories.isNotEmpty()) {
                val data = MutableLiveData<List<ChildCategory>>()
                data.value = childCategories
                val listAdapter = ChildListAdapter(data)
                holder.recycelerview.apply {
                    adapter = listAdapter
                    layoutManager = LinearLayoutManager(context)
                }
                holder.itemlayout.setOnClickListener {
                    println("setonclick count ")
                    when {
                        holder.child.visibility == View.GONE -> {
                            expand(holder.child)
                        }
                        else -> {
                            collapse(holder.child)
                        }
                    }
                }
            }
        }
    }

    class CommonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image = view.image!!
        var name = view.name!!
        var recycelerview = view.child_recycelerview!!
        var itemlayout = view.layout!!
        var child = view.child!!
    }

}