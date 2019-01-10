package com.alhpa.narsinhaproducts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alhpa.narsinhaproducts.R;
import com.alhpa.narsinhaproducts.activity.AddArticleNameActivity;
import com.alhpa.narsinhaproducts.utility.AppDatabase;

import java.util.List;

public class ArticleNameAdapter extends RecyclerView.Adapter<ArticleNameAdapter.MyViewHolder> {

    private Context context;
    private List<String> articleNameTableList;
    private boolean isSelectMode;

    public ArticleNameAdapter(Context context, List<String> articleNameTableList, boolean isSelectMode) {
        this.context = context;
        this.articleNameTableList = articleNameTableList;
        this.isSelectMode = isSelectMode;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article_name, viewGroup, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tvArticleName.setText(articleNameTableList.get(i));

        if ((i + 1) == articleNameTableList.size()) {
            myViewHolder.view.setVisibility(View.GONE);
        } else {
            myViewHolder.view.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return articleNameTableList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivDeleteArticleName;
        private TextView tvArticleName;
        private View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivDeleteArticleName = itemView.findViewById(R.id.ivDeleteArticleName);
            tvArticleName = itemView.findViewById(R.id.tvArticleName);
            view = itemView.findViewById(R.id.view);

            if(isSelectMode){
                ivDeleteArticleName.setVisibility(View.GONE);
                itemView.setClickable(true);
            }
            else{
                ivDeleteArticleName.setVisibility(View.VISIBLE);
                itemView.setClickable(false);
            }

            ivDeleteArticleName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AppDatabase.getInstance(context).articleNameTableDao().deleteByName(articleNameTableList.get(getAdapterPosition()));

                    articleNameTableList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ((AddArticleNameActivity)context).articleSelected(articleNameTableList.get(getAdapterPosition()));

                }
            });

        }
    }

}
