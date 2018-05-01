package au.com.inteliment.poc.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.com.inteliment.poc.R
import kotlinx.android.synthetic.main.layout_item.view.*

/**
 * Created by n1120461 on 27/04/2018.
 */
class ItemAdapter(context: Context, private var items: List<String>, private var callback: ItemClickCallback?) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    var inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        val view = inflater.inflate(R.layout.layout_item, parent, false)
        val holder = ItemViewHolder(view)
        view.setOnClickListener { callback?.onItemClick(items[holder.adapterPosition]) }
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        holder?.binData(items[position])
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun binData(item: String) {
            itemView.itemName.text = item
        }
    }

    interface ItemClickCallback {
        fun onItemClick(item: String)
    }
}