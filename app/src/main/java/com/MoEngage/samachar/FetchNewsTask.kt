package com.MoEngage.samachar

// FetchNewsTask.kt
import android.os.AsyncTask
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class FetchNewsTask(private val listener: OnNewsFetchedListener) : AsyncTask<Void, Void, List<NewsItem>>() {

    interface OnNewsFetchedListener {
        fun onNewsFetched(newsList: List<NewsItem>?)
    }

    override fun doInBackground(vararg params: Void?): List<NewsItem>? {
        try {
            val url = URL("https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json")
            val connection = url.openConnection() as HttpURLConnection

            val inputStream = connection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))

            val stringBuilder = StringBuilder()
            var line: String?

            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }

            bufferedReader.close()
            inputStream.close()

            val response = stringBuilder.toString()
            return parseJson(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun onPostExecute(result: List<NewsItem>?) {
        listener.onNewsFetched(result)
    }

    private fun parseJson(json: String): List<NewsItem>? {
        try {
            val jsonObject = JSONObject(json)
            val articlesArray = jsonObject.getJSONArray("articles")

            val newsList = mutableListOf<NewsItem>()

            for (i in 0 until articlesArray.length()) {
                val articleObject: JSONObject = articlesArray.getJSONObject(i)

                val sourceObject = articleObject.getJSONObject("source")
                val source = Source(sourceObject.getString("id"), sourceObject.getString("name"))

                val author = articleObject.getString("author")
                val title = articleObject.getString("title")
                val description = articleObject.getString("description")
                val url = articleObject.getString("url")
                val urlToImage = articleObject.getString("urlToImage")
                val publishedAt = articleObject.getString("publishedAt")
                val content = articleObject.getString("content")

                val newsItem = NewsItem(source, author, title, description, url, urlToImage, publishedAt, content)
                newsList.add(newsItem)
            }

            return newsList
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}

