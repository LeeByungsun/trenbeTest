package com.trenbe.test.ui.main.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.trenbe.test.R
import com.trenbe.test.Util.setImage
import com.trenbe.test.network.vo.ChildCategory
import kotlinx.android.synthetic.main.item_child.view.*


class ChildListAdapter(childData: MutableLiveData<List<ChildCategory>>) :
    RecyclerView.Adapter<ChildListAdapter.CommonViewHolder>() {

    private var childList: MutableLiveData<List<ChildCategory>>? = childData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_child, parent, false)
        return CommonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return childList!!.value?.size ?: 0
    }


    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val categori = childList!!.value!![position]
        categori.run {
            holder.name.text = name
            setImage(images, holder.image)
        }
    }

    class CommonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image = view.child_image!!
        var name = view.child_name!!
    }
}