package com.alhpa.narsinhaproducts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alhpa.narsinhaproducts.R;
import com.alhpa.narsinhaproducts.table.ArticleTable;
import com.alhpa.narsinhaproducts.utility.AppDatabase;

import java.util.List;

public class ArticleStockVariationAdapter extends RecyclerView.Adapter<ArticleStockVariationAdapter.MyViewHolder> {

    private Context context;
    private List<ArticleTable> articleTableList;

    public ArticleStockVariationAdapter(Context context, List<ArticleTable> articleTableList) {
        this.context = context;
        this.articleTableList = articleTableList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article_stock_variation, viewGroup, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tvColor.setText(articleTableList.get(i).getColorName());
        myViewHolder.tvRemainingQuantity.setText(""+articleTableList.get(i).getQuantityRemaining());

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

        private TextView tvRemainingQuantity;
        private TextView tvColor;
        private View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRemainingQuantity = itemView.findViewById(R.id.tvRemainingQuantity);
            tvColor = itemView.findViewById(R.id.tvColor);
            view = itemView.findViewById(R.id.view);

        }
    }

}
