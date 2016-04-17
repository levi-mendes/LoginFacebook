package br.com.levimendes.teste.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.levimendes.teste.R;
import br.com.levimendes.teste.bean.Post;

/**
 * Created by Levi on 16/04/2016.
 */
public class ListaPostsAdapter extends RecyclerView.Adapter<ListaPostsAdapter.ViewHolder> {


    List<Post> mListaPosts;


    public ListaPostsAdapter(ArrayList<Post> listaPosts) {
        mListaPosts = listaPosts;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.linha_post, parent, false);
        final ViewHolder vh = new ViewHolder(v);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Post post = mListaPosts.get(position);

        holder.etTitulo.setText(post.story);
        holder.etData.setText(post.createdTime);
        holder.etMensagem.setText(post.message);
    }

    @Override
    public int getItemCount() {
        return mListaPosts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public EditText etTitulo;
        public EditText etData;
        public EditText etMensagem;

        public ViewHolder(View v) {
            super(v);

            etTitulo   = (EditText)v.findViewById(R.id.etTitulo);
            etData     = (EditText)v.findViewById(R.id.etData);
            etMensagem = (EditText)v.findViewById(R.id.etMensagem);
        }
    }

}
