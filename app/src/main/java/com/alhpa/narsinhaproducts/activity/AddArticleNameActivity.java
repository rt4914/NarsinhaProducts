package com.alhpa.narsinhaproducts.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alhpa.narsinhaproducts.R;
import com.alhpa.narsinhaproducts.adapter.ArticleNameAdapter;
import com.alhpa.narsinhaproducts.table.ArticleNameTable;
import com.alhpa.narsinhaproducts.utility.AppDatabase;

import java.util.List;

public class AddArticleNameActivity extends AppCompatActivity {

    private static final String TAG = AddArticleNameActivity.class.getSimpleName();

    private boolean isSelectMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article_name);

        Intent i = getIntent();

        if(i!=null){
            isSelectMode = i.getBooleanExtra("SELECT_ARTICLE", false);
        }

        showRecyclerView();

    }

    private void showRecyclerView() {

        RecyclerView rvArticle = findViewById(R.id.rvArticle);
        rvArticle.setLayoutManager(new LinearLayoutManager(this));

        List<String> articleNameTableList = AppDatabase.getInstance(this).articleNameTableDao().getAllArticleNonLive();

        Log.d(TAG, "showRecyclerView: " + articleNameTableList.size());

        if (!articleNameTableList.isEmpty()) {
            rvArticle.setVisibility(View.VISIBLE);
            ArticleNameAdapter adapter = new ArticleNameAdapter(this, articleNameTableList, isSelectMode);
            rvArticle.setAdapter(adapter);
        } else {
            rvArticle.setVisibility(View.GONE);
        }

    }

    public void addArticleMethod(View view) {

        EditText etArticle = findViewById(R.id.etArticle);

        String sArticle = etArticle.getText().toString().trim();

        if (sArticle.length() > 0) {
            Toast.makeText(this, "Article name added", Toast.LENGTH_SHORT).show();
            ArticleNameTable articleNameTable = new ArticleNameTable();
            articleNameTable.setArticleName(sArticle.toUpperCase());
            AppDatabase.getInstance(this).articleNameTableDao().insert(articleNameTable);
            showRecyclerView();
            etArticle.setText("");
        } else {
            Toast.makeText(this, "Enter article name", Toast.LENGTH_SHORT).show();
        }

    }

    public void articleSelected(String articleName) {

        Intent i = new Intent();
        i.putExtra("ARTICLE_NAME", articleName);
        setResult(Activity.RESULT_OK, i);
        finish();

    }

}
