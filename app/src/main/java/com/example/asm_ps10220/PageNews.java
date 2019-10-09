package com.example.asm_ps10220;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class PageNews extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_news);

        webView = findViewById(R.id.webview);
        Intent intent = getIntent();
        String url = intent.getStringExtra("link");
        webView.loadUrl(url);
    }
}
