package com.MoEngage.samachar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(private val originalList: List<NewsItem>, private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>(), Filterable {

    private var filteredList: List<NewsItem> = originalList

    interface OnItemClickListener {
        fun onItemClick(url: String)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

        init {
            titleTextView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = filteredList[position]
                    onItemClickListener.onItemClick(clickedItem.url ?: "")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = filteredList[position]
        holder.titleTextView.text = currentItem.title
        holder.descriptionTextView.text = currentItem.description
        // Bind other views as needed
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()

                if (constraint.isNullOrBlank()) {
                    filterResults.values = originalList
                } else {
                    val filtered = originalList.filter { newsItem ->
                        newsItem.title?.contains(constraint, ignoreCase = true) == true
                    }
                    filterResults.values = filtered
                }

                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as? List<NewsItem> ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}
