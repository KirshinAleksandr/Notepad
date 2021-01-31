package site.kirshin.notepad.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import site.kirshin.notepad.R

class DataAdapter(list: ArrayList<String>) : Adapter<DataAdapter.Holder>() {

    var listArray = list

    class Holder(itemView: View) : ViewHolder(itemView) {

        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)

        fun setData(title: String) {
            tvTitle.text = title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(inflater.inflate(R.layout.rc_item, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // связываем view и данные из array list
        holder.setData(listArray.get(position))
    }

    override fun getItemCount(): Int {
        // возвращаем количество элементов в массиве
        return listArray.size
    }

    fun updateAdapter(listItems: List<String>) {
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }
}