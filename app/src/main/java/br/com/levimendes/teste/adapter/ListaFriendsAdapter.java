package br.com.levimendes.teste.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import br.com.levimendes.teste.bean.Friend;
import br.com.levimendes.teste.R;
import br.com.levimendes.teste.util.CircleTransform;
import br.com.levimendes.teste.util.ToastUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 809778 on 02/05/2016.
 */
public class ListaFriendsAdapter extends RecyclerView.Adapter<ListaFriendsAdapter.ViewHolder> {

    public List<Friend> mDataset;

    public ListaFriendsAdapter(List<Friend> myDataset) {
        mDataset= myDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        Friend friend = mDataset.get(position);

        holder.tvId.setText(friend.id);
        holder.tvName.setText(friend.name);
        holder.itemView.setOnClickListener(v -> ToastUtil.showLong(context, "Name: " + friend.name));

        Picasso.with(context)
              .load(friend.urlPicture)
              .transform(new CircleTransform())
              .into(holder.ivPicture);
    }

    @Override
    public int getItemCount() {
        return mDataset != null && mDataset.size()> 0 ? mDataset.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivPicture)
        ImageView ivPicture;
        @BindView(R.id.tvId)
        TextView tvId;
        @BindView(R.id.tvName)
        TextView tvName;

        public ViewHolder(View v) {
            super(v);

            ButterKnife.bind(this, v);
        }
    }
}