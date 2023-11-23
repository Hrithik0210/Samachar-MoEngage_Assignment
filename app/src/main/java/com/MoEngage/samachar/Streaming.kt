package com.MoEngage.samachar

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class Streaming : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_streaming)

        val cardView: CardView = findViewById(R.id.card_view)
        cardView.setOnClickListener {
            // Open the YouTube link when the CardView is clicked
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LVsvU6cqzRo"))
            startActivity(intent)
        }

        val cardView1: CardView = findViewById(R.id.img1)
        cardView1.setOnClickListener {
            // Open the YouTube link when the CardView is clicked
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LVsvU6cqzRo"))
            startActivity(intent)
        }

        val cardView2: CardView = findViewById(R.id.img2)
        cardView2.setOnClickListener {
            // Open the YouTube link when the CardView is clicked
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=DDZEXoT8DE8"))
            startActivity(intent)
        }

        val cardView3: CardView = findViewById(R.id.img3)
        cardView3.setOnClickListener {
            // Open the YouTube link when the CardView is clicked
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LVsvU6cqzRo"))
            startActivity(intent)
        }


        val cardView4: CardView = findViewById(R.id.img4)
        cardView4.setOnClickListener {
            // Open the YouTube link when the CardView is clicked
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=nyd-xznCpJc"))
            startActivity(intent)
        }


        val cardView5: CardView = findViewById(R.id.img5)
        cardView5.setOnClickListener {
            // Open the YouTube link when the CardView is clicked
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Nq2wYlWFucg"))
            startActivity(intent)
        }


        val cardView6: CardView = findViewById(R.id.img6)
        cardView6.setOnClickListener {
            // Open the YouTube link when the CardView is clicked
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LVsvU6cqzRo"))
            startActivity(intent)
        }

        val cardView7: CardView = findViewById(R.id.img7)
        cardView7.setOnClickListener {
            // Open the YouTube link when the CardView is clicked
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LVsvU6cqzRo"))
            startActivity(intent)
        }


        val cardView8: CardView = findViewById(R.id.img8)
        cardView8.setOnClickListener {
            // Open the YouTube link when the CardView is clicked
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LVsvU6cqzRo"))
            startActivity(intent)
        }


        val cardView9: CardView = findViewById(R.id.img9)
        cardView9.setOnClickListener {
            // Open the YouTube link when the CardView is clicked
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LVsvU6cqzRo"))
            startActivity(intent)
        }
    }
}