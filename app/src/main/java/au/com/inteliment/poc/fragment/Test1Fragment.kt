package au.com.inteliment.poc.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import au.com.inteliment.poc.R
import au.com.inteliment.poc.adapter.FragmentItemAdapter
import au.com.inteliment.poc.adapter.ItemAdapter
import kotlinx.android.synthetic.main.test1_fragment.*

/**
 * Created by n1120461 on 30/04/2018.
 */
class Test1Fragment : Fragment(), ItemAdapter.ItemClickCallback {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.test1_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        initRecyclerView(recyclerView)
        initViewPager(viewPager)
        buttonRed?.setOnClickListener { v -> onClickButton(v) }
        buttonGreen?.setOnClickListener { v -> onClickButton(v) }
        buttonBlue?.setOnClickListener { v -> onClickButton(v) }
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
        val itemDecoration = DividerItemDecoration(activity, layoutManager.orientation)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.adapter = ItemAdapter(activity, resources.getStringArray(R.array.item_list).asList(), this)
    }

    private fun initViewPager(viewPager: ViewPager) {
        val fragmentAdapter = FragmentItemAdapter(childFragmentManager)
        for (i in 1..4) {
            val title = getString(R.string.fragment_name, i)
            fragmentAdapter.addFragment(MainFragment.instance(title), title)
        }
        viewPager.adapter = fragmentAdapter
        pageIndicator.setViewPager(viewPager)
    }

    override fun onItemClick(item: String) {
        clickedItemName.text = item
    }

    fun onClickButton(view: View) {
        when (view.id) {
            R.id.buttonRed -> changeAppBackground(R.color.red)
            R.id.buttonBlue -> changeAppBackground(R.color.blue)
            R.id.buttonGreen -> changeAppBackground(R.color.green)
        }
    }

    private fun changeAppBackground(bgColor: Int) {
        parentContainer.setBackgroundColor(ContextCompat.getColor(activity, bgColor))
    }

    companion object {
        fun instance(): Test1Fragment {
            return Test1Fragment()
        }
    }
}