package com.market.onshopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.market.onshopping.INterface.AdapterCallback;
import com.market.onshopping.R;
import com.market.onshopping.activity.CheckoutBillActivity;
import com.market.onshopping.model.DatabaseHelper;
import com.market.onshopping.model.Note;

import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.MyViewHolder> {

    private List<Note> moviesList;
    int totalamt = 0;
    Context context;
    String Name;
    private DatabaseHelper db;
    private int quantity = 1;
    private AdapterCallback callback;
    List<Note> notesList;
    public CheckoutAdapter(Class<? extends List> aClass) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView itemimage, cartminus, cartplus, deleteCart;
        public Button pluse, minus;
        public TextView name, rupees, itweight, itemamt, cart_product, vegtotal, quantityProductPage;
        public LinearLayout linearLayout;
        TextView addcart;
        private RelativeLayout addcartlinear, addAndminusLinear;
        int firstClick = 0;


        public MyViewHolder(View view) {
            super(view);
            db = new DatabaseHelper(context);
            itemimage = (ImageView) view.findViewById(R.id.list_image);
            deleteCart = (ImageView) view.findViewById(R.id.deletecard);
            minus = (Button) view.findViewById(R.id.cart_minus_img);
            pluse = (Button) view.findViewById(R.id.cart_plus_img);
            name = (TextView) view.findViewById(R.id.from_name);
            itemamt = (TextView) view.findViewById(R.id.plist_weight_text);
            itweight = (TextView) view.findViewById(R.id.plist_price_text);
            rupees = (TextView) view.findViewById(R.id.plist_weight_text);
            //cart_product = (TextView) view.findViewById(R.id.cart_product);
            vegtotal = (TextView) view.findViewById(R.id.plist_weight_text1);
            quantityProductPage = (TextView) view.findViewById(R.id.quantityProductPage);
            linearLayout = (LinearLayout) view.findViewById(R.id.linear);

            addcartlinear = (RelativeLayout) view.findViewById(R.id.addcartlinear);
            addAndminusLinear = (RelativeLayout) view.findViewById(R.id.add_and_minus);


        }

    }


    public CheckoutAdapter(List<Note> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopbycategory_copy, parent, false);
        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Note note = moviesList.get(position);

        db.getAllNotes();

        List<Note> notesList = db.getAllNotes();
        int size = notesList.size();
        holder.name.setText(note.getTitle());
        holder.rupees.setText(note.getAmount());
        holder.itemamt.setText("Rs " + note.getAmount());

        holder.quantityProductPage.setText(note.getItemCount());
        Glide.with(context).load(note.getImage()).into(holder.itemimage);

        holder.itemimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        holder.deleteCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notee=note.getNote();
                db.delete_byID(Integer.parseInt(notee));
                Intent i = new Intent(context, CheckoutBillActivity.class);
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}