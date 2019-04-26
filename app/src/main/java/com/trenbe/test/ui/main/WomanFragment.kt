package com.trenbe.test.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.trenbe.test.R
import com.trenbe.test.network.vo.Categori
import com.trenbe.test.ui.main.adapter.WomanListAdapter
import kotlinx.android.synthetic.main.fragment_woman.*

/**
 * A placeholder fragment containing a simple view.
 */
class WomanFragment : Fragment() {

    private lateinit var pageViewModel: WomanPageViewModel
    private lateinit var mAdapter: WomanListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        val TAG = javaClass.simpleName

        super.onCreate(savedInstanceState)
        println(TAG)
        pageViewModel = ViewModelProviders.of(this).get(WomanPageViewModel::class.java).apply {
//            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
            getList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_woman, container, false)
//        val textView: TextView = root.findViewById(R.id.section_label)
//        pageViewModel.text.observe(this, Observer<String> {
//            textView.text = it
//        })
        pageViewModel.categoryList?.observe(this, Observer {
            val data = MutableLiveData<List<Categori>>()
            data.value = it
            mAdapter = WomanListAdapter(data)

            recyclerview.adapter = mAdapter

            recyclerview.layoutManager = LinearLayoutManager(activity)
        })
        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): WomanFragment {
            return WomanFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}