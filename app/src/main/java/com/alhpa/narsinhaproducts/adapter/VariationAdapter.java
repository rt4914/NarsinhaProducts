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
import com.alhpa.narsinhaproducts.table.ArticleDispatchTable;
import com.alhpa.narsinhaproducts.table.ArticleTable;
import com.alhpa.narsinhaproducts.utility.AppDatabase;

import java.util.List;

public class VariationAdapter extends RecyclerView.Adapter<VariationAdapter.MyViewHolder> {

    private Context context;
    private List<ArticleTable> articleTableList;
    private List<ArticleDispatchTable> articleDispatchTableList;
    private boolean isEditMode;

    public VariationAdapter(Context context, List<ArticleTable> articleTableList, boolean isEditMode) {
        this.context = context;
        this.articleTableList = articleTableList;
        this.isEditMode = isEditMode;
    }

    public VariationAdapter(Context context, List<ArticleDispatchTable> articleDispatchTableList) {
        this.context = context;
        this.articleDispatchTableList = articleDispatchTableList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article_variation, viewGroup, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        if (articleTableList != null) {

            myViewHolder.tvColor.setText(articleTableList.get(i).getColorName());
            myViewHolder.tvRemainingQuantity.setText("" + articleTableList.get(i).getQuantityRemaining());

            if ((i + 1) == articleTableList.size()) {
                myViewHolder.view.setVisibility(View.GONE);
            } else {
                myViewHolder.view.setVisibility(View.VISIBLE);
            }
        } else {
            myViewHolder.tvColor.setText(articleDispatchTableList.get(i).getArticleDispatchName() + " - " + articleDispatchTableList.get(i).getColorName());
            myViewHolder.tvRemainingQuantity.setText("" + articleDispatchTableList.get(i).getQuantityDispatched());

            if ((i + 1) == articleDispatchTableList.size()) {
                myViewHolder.view.setVisibility(View.GONE);
            } else {
                myViewHolder.view.setVisibility(View.VISIBLE);
            }

        }

    }

    @Override
    public int getItemCount() {
        if (articleDispatchTableList != null) {
            return articleDispatchTableList.size();
        } else {
            return articleTableList.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivDeleteItem;
        private TextView tvRemainingQuantity;
        private TextView tvColor;
        private View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivDeleteItem = itemView.findViewById(R.id.ivDeleteItem);
            tvRemainingQuantity = itemView.findViewById(R.id.tvRemainingQuantity);
            tvColor = itemView.findViewById(R.id.tvColor);
            view = itemView.findViewById(R.id.view);

            ivDeleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (articleTableList != null) {
                        AppDatabase.getInstance(context).articleTableDao().deleteByNameAndColor(articleTableList.get(getAdapterPosition()).getArticleName(), articleTableList.get(getAdapterPosition()).getColorName());

                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();

                        articleTableList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                    } else if (articleDispatchTableList != null) {

                        int iQuantity = articleDispatchTableList.get(getAdapterPosition()).getQuantityDispatched();

                        ArticleTable articleTable = AppDatabase.getInstance(context).articleTableDao().getAllArticleByArticleNameAndColorNonLive(articleDispatchTableList.get(getAdapterPosition()).getArticleDispatchName(), articleDispatchTableList.get(getAdapterPosition()).getColorName());
                        int iTotal = articleTable.getQuantityRemaining();
                        articleTable.setQuantityRemaining((iTotal + iQuantity));

                        AppDatabase.getInstance(context).articleTableDao().insert(articleTable);

                        AppDatabase.getInstance(context).articleDispatchTableDao().deleteByNameAndColor(articleDispatchTableList.get(getAdapterPosition()).getArticleDispatchName(), articleDispatchTableList.get(getAdapterPosition()).getColorName());

                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();

                        articleDispatchTableList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());

                    }

                }
            });

        }
    }

}
