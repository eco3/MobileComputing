package de.mobilecomputing.exercise3.networkarchitecture;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private final List<User> users;

    public UserAdapter() {
        this.users = new ArrayList<>();
    }

    public void updateData(final List<User> users) {
        this.users.clear();
        this.users.addAll(users);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.nameLabel.setText(users.get(position).toString());

        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.cardView.getContext(), UserViewActivity.class);
            intent.putExtra(UserViewActivity.EXTRA_USER_ID, users.get(position).id);

            holder.cardView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameLabel;
        CardView cardView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            nameLabel = (TextView) itemView.findViewById(R.id.name_label);
            cardView = (CardView) itemView.findViewById(R.id.user_cardview);
        }
    }
}
