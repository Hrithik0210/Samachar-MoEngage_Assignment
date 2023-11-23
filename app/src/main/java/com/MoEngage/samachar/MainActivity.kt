package com.MoEngage.samachar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), FetchNewsTask.OnNewsFetchedListener, NewsAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var spinnerSort: Spinner
    private lateinit var adapter: NewsAdapter
    private var newsList: List<NewsItem> = emptyList()
    private var isSortingOldToNew = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, Streaming::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        spinnerSort = findViewById(R.id.spinnerSort)

        recyclerView.layoutManager = LinearLayoutManager(this)

        spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                toggleSortOrder(position == 0) // If position is 0, sort by newest
                sortAndSetAdapter()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing
            }
        }

        // Set up search functionality
        setupSearchView()

        FetchNewsTask(this).execute()
    }

    private fun toggleSortOrder(newestFirst: Boolean) {
        isSortingOldToNew = !newestFirst
    }

    private fun sortAndSetAdapter() {
        val sortedList = if (isSortingOldToNew) {
            newsList.sortedBy { it.publishedAt }
        } else {
            newsList.sortedByDescending { it.publishedAt }
        }

        adapter = NewsAdapter(sortedList, this)
        recyclerView.adapter = adapter
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Perform search based on the query
                adapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Perform search based on the changed query
                adapter.filter.filter(newText)
                return true
            }
        })

        // If you want to handle close button click
        searchView.setOnCloseListener {
            // Clear the filter when the search view is closed
            adapter.filter.filter(null)
            false
        }
    }

    override fun onNewsFetched(newsList: List<NewsItem>?) {
        if (newsList != null) {
            this.newsList = newsList
            sortAndSetAdapter()
        }
    }

    override fun onItemClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}
