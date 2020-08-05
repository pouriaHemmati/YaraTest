package org.pouria.yara.mvp.search.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.pouria.yara.R;
import org.pouria.yara.base.BaseActivity;
import org.pouria.yara.model.JSearch;

import java.util.ArrayList;


public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder> {
    private ModelItemSearch requestItems;
    private IOnClickMovie iOnClickMovie;
    private Context context;
    private Picasso picasso;


    public SearchRecyclerAdapter(Context context, ModelItemSearch requestItems, IOnClickMovie iOnClickMovie) {
        this.context = context;
        this.requestItems = requestItems;
        this.iOnClickMovie = iOnClickMovie;


    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView logo_recycler;
        public TextView txt_title_recycler;
        public TextView txt_year_recycler;
        public TextView txt_type_recycler;
        public CardView layout_search;


        public ViewHolder(View itemView) {
            super(itemView);

            logo_recycler = itemView.findViewById(R.id.logo_recycler);
            txt_title_recycler = itemView.findViewById(R.id.txt_title_recycler);
            txt_year_recycler = itemView.findViewById(R.id.txt_year_recycler);
            txt_type_recycler = itemView.findViewById(R.id.txt_type_recycler);
            layout_search = itemView.findViewById(R.id.layout_search);
            picasso = Picasso.get();

        }

        @Override
        public void onClick(View v) {

        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_search, parent, false);
        return new ViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArrayList<JSearch> jSearch = requestItems.jResponse;


        if (jSearch.get(position).Poster != null) {
            picasso.load(jSearch.get(position).Poster)
                    .into(holder.logo_recycler);
        }

        if (jSearch.get(position).Title != null)
            holder.txt_title_recycler.setText(jSearch.get(position).Title);


        if (jSearch.get(position).Type != null)
            holder.txt_type_recycler.setText(jSearch.get(position).Type);

        if (jSearch.get(position).Year != null)
            holder.txt_year_recycler.setText("Year : " +jSearch.get(position).Year);


        holder.layout_search.setOnClickListener(v -> {
            iOnClickMovie.onClickMovie(jSearch.get(position).imdbID);
        });


    }


    @Override
    public int getItemCount() {
        return requestItems.jResponse.size();
    }


}