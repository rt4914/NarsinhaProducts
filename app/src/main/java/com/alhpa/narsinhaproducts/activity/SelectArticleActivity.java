package com.alhpa.narsinhaproducts.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alhpa.narsinhaproducts.R;
import com.alhpa.narsinhaproducts.adapter.ArticleAdapter;
import com.alhpa.narsinhaproducts.table.ArticleTable;
import com.alhpa.narsinhaproducts.utility.AppDatabase;

import java.util.List;

public class SelectArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_article);

        showRecyclerView();

    }

    private void showRecyclerView() {

        List<ArticleTable> articleTableList = AppDatabase.getInstance(this).articleTableDao().getAllArticleNonLive();

        RecyclerView rvArticle = findViewById(R.id.rvArticle);
        rvArticle.setLayoutManager(new LinearLayoutManager(this));

        if (articleTableList != null && !articleTableList.isEmpty()) {
            rvArticle.setVisibility(View.VISIBLE);
            ArticleAdapter adapter = new ArticleAdapter(this, articleTableList);
            rvArticle.setAdapter(adapter);
        } else {
            rvArticle.setVisibility(View.GONE);
        }

    }

    public void articleSelected(String articleName, String color) {

        Intent i = new Intent();
        i.putExtra("ARTICLE_NAME", articleName);
        i.putExtra("COLOR", color);
        setResult(Activity.RESULT_OK, i);
        finish();

    }


}
