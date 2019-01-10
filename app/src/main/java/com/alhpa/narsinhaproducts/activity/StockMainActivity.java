package com.alhpa.narsinhaproducts.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alhpa.narsinhaproducts.R;
import com.alhpa.narsinhaproducts.adapter.ProductionAdapter;
import com.alhpa.narsinhaproducts.adapter.StockMainAdapter;
import com.alhpa.narsinhaproducts.table.ArticleTable;
import com.alhpa.narsinhaproducts.utility.AppDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockMainActivity extends AppCompatActivity {

    private List<ArticleTable> articleTableList;

    private HashMap<String, Integer> articleRemainingQuantityHashMap;

    private List<String> articleNameList;
    private List<Integer> articleRemainingQuantityList;

    private int iTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_main);

        articleRemainingQuantityHashMap = new HashMap<>();

        articleNameList = new ArrayList<>();
        articleRemainingQuantityList = new ArrayList<>();

        showRecyclerView();

    }

    private void showRecyclerView() {

        articleTableList = AppDatabase.getInstance(this).articleTableDao().getAllArticleNonLive();

        for (int i = 0; i < articleTableList.size(); i++) {

            String sArticleName = articleTableList.get(i).getArticleName();
            int iTotalQuantityInThisArticle = 0;
            if (articleRemainingQuantityHashMap.containsKey(sArticleName)) {
                iTotalQuantityInThisArticle = articleRemainingQuantityHashMap.get(sArticleName);
            }
            iTotalQuantityInThisArticle = iTotalQuantityInThisArticle + articleTableList.get(i).getQuantityRemaining();
            articleRemainingQuantityHashMap.put(sArticleName, iTotalQuantityInThisArticle);
        }

        for(String s: articleRemainingQuantityHashMap.keySet()){

            int i = articleRemainingQuantityHashMap.get(s);

            articleNameList.add(s);
            articleRemainingQuantityList.add(i);
        }

        RecyclerView rvArticleTotal = findViewById(R.id.rvArticleTotal);
        rvArticleTotal.setLayoutManager(new LinearLayoutManager(this));

        if (articleTableList != null && !articleTableList.isEmpty()) {
            rvArticleTotal.setVisibility(View.VISIBLE);
            StockMainAdapter adapter = new StockMainAdapter(this, articleNameList, articleRemainingQuantityList);
            rvArticleTotal.setAdapter(adapter);
        } else {
            rvArticleTotal.setVisibility(View.GONE);
        }

    }

}
