package com.sample.androidparser.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.androidparser.R
import com.sample.androidparser.data.model.Parser
import kotlinx.android.synthetic.main.item_layout_data.view.*

class DataAdapter(private val parserList: ArrayList<Parser>) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    private var mlistener: OnItemClickListener? = null

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(parser: Parser) {
            itemView.apply {
                tv_name.text = parser.name
                tv_tag.text = parser.tag
                if (parser.color == "green")
                    tv_tag.setTextColor(Color.parseColor("#3CC501"))
                else if (parser.color == "red")
                    tv_tag.setTextColor(Color.parseColor("#E80000"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout_data, parent, false)
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(parserList[position])
        holder.itemView.setOnClickListener {
            mlistener?.onItemClick(it, parserList[position])
        }
    }

    override fun getItemCount(): Int = parserList.size

    fun addData(parserList: List<Parser>) {
        this.parserList.apply {
            clear()
            addAll(parserList)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, parser: Parser?)
    }

    fun setOnSearchItemClickListener(listener: OnItemClickListener) {
        mlistener = listener
    }
}