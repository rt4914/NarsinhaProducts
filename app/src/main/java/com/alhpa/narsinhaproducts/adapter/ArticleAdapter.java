package com.alhpa.narsinhaproducts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alhpa.narsinhaproducts.R;
import com.alhpa.narsinhaproducts.activity.SelectArticleActivity;
import com.alhpa.narsinhaproducts.table.ArticleTable;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {

    private Context context;
    private List<ArticleTable> articleTableList;

    public ArticleAdapter(Context context, List<ArticleTable> articleTableList) {
        this.context = context;
        this.articleTableList = articleTableList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article, viewGroup, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tvArticleName.setText(articleTableList.get(i).getArticleName() + " - " + articleTableList.get(i).getColorName());

        if ((i + 1) == articleTableList.size()) {
            myViewHolder.view.setVisibility(View.GONE);
        } else {
            myViewHolder.view.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return articleTableList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvArticleName;
        private View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvArticleName = itemView.findViewById(R.id.tvArticleName);
            view = itemView.findViewById(R.id.view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ((SelectArticleActivity) context).articleSelected(articleTableList.get(getAdapterPosition()).getArticleName(), articleTableList.get(getAdapterPosition()).getColorName());

                }
            });

        }
    }

}
