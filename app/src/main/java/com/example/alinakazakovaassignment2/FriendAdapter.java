package com.example.alinakazakovaassignment2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    private MainActivity ma = new MainActivity();
    public static final String NAME ="name";
    public static final String NUMBER ="number";
    public static final String EMAIL ="email";
    public static final String POSITION ="position";

    private List<Friend> friends;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nameTextView;
        public TextView numTextView;
        public TextView emailTextView;


        public ViewHolder(View itemView)  {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.name);
            numTextView = (TextView) itemView.findViewById(R.id.num);
            emailTextView = (TextView) itemView.findViewById(R.id.email);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getLayoutPosition();
            Friend f = friends.get(position);
            String name = f.getName();
            String number = f.getNum();
            String email = f.getEmail();
            Intent intent = new Intent(view.getContext(), EditFriendActivity.class);
            intent.putExtra(FriendAdapter.NAME, name);
            intent.putExtra(FriendAdapter.NUMBER, number);
            intent.putExtra(FriendAdapter.EMAIL, email);
            intent.putExtra(FriendAdapter.POSITION, position);
            view.getContext().startActivity(intent);
        }

    }

    // Pass in the contact array into the constructor
    public FriendAdapter(List<Friend> friends, Context context) {
        this.friends = friends;
        this.context = context;
    }

    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View friendView = inflater.inflate(R.layout.row_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(friendView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FriendAdapter.ViewHolder viewHolder, final int position) {
        Friend friend = friends.get(position);
        TextView nameTxt = viewHolder.nameTextView;
        TextView numTxt = viewHolder.numTextView;
        TextView emailTxt = viewHolder.emailTextView;
        nameTxt.setText(friend.getName());
        numTxt.setText(friend.getNum());
        emailTxt.setText(friend.getEmail());
        final String name = nameTxt.getText().toString();
    }

            // Returns the total count of items in the list
            @Override
            public int getItemCount() {
                return friends.size();
            }


}
