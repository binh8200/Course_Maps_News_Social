package com.example.asm_ps10220.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.asm_ps10220.Class.NewsReader;
import com.example.asm_ps10220.Model.News;
import com.example.asm_ps10220.PageNews;
import com.example.asm_ps10220.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FragmentNews extends Fragment {

    ListView lvNews;
    News news;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        lvNews = view.findViewById(R.id.lvNews);
        NewsSeedAsyncTask newsSeedAsyncTask = new NewsSeedAsyncTask();
        newsSeedAsyncTask.execute();
        return view;
    }

    class NewsSeedAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("https://www.theguardian.com/education/rss");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                final List<News> newses = NewsReader.listNews(inputStream);
                final ListAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, newses);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lvNews.setAdapter(adapter);
                        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                news = new News();
                                news = newses.get(position);
                                String link = news.getLink();
                                Intent intent = new Intent(getContext(), PageNews.class);
                                intent.putExtra("link", link);
                                startActivity(intent);
                            }
                        });
                    }
                });

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
