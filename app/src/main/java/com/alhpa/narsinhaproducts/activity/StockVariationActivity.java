package com.alhpa.narsinhaproducts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alhpa.narsinhaproducts.R;
import com.alhpa.narsinhaproducts.adapter.ArticleStockVariationAdapter;
import com.alhpa.narsinhaproducts.adapter.ProductionAdapter;
import com.alhpa.narsinhaproducts.adapter.StockMainAdapter;
import com.alhpa.narsinhaproducts.table.ArticleTable;
import com.alhpa.narsinhaproducts.utility.AppDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockVariationActivity extends AppCompatActivity {

    private List<ArticleTable> articleTableList;

    private int iTotal = 0;

    private String sArticleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_variation);

        Intent i = getIntent();

        if(i!=null){
            sArticleName = i.getStringExtra("ARTICLE_NAME");
        }
        else{
            finish();
            return;
        }

        showRecyclerView();

    }

    private void showRecyclerView() {

        TextView tvArticleName  = findViewById(R.id.tvArticleName);
        TextView tvTotalRemainingQuantity  = findViewById(R.id.tvTotalRemainingQuantity);

        articleTableList = AppDatabase.getInstance(this).articleTableDao().getAllArticleByArticleNameNonLive(sArticleName);

        int iRemainingQuantity = 0;

        for(int i = 0 ; i <articleTableList.size(); i++){

            iRemainingQuantity = iRemainingQuantity + articleTableList.get(i).getQuantityRemaining();

        }

        tvArticleName.setText(sArticleName);
        tvTotalRemainingQuantity.setText(""+iRemainingQuantity);

        RecyclerView rvArticleVariation = findViewById(R.id.rvArticleVariation);
        rvArticleVariation.setLayoutManager(new LinearLayoutManager(this));

        if (articleTableList != null && !articleTableList.isEmpty()) {
            rvArticleVariation.setVisibility(View.VISIBLE);
            ArticleStockVariationAdapter adapter = new ArticleStockVariationAdapter(this, articleTableList);
            rvArticleVariation.setAdapter(adapter);
        } else {
            rvArticleVariation.setVisibility(View.GONE);
        }

    }

}
