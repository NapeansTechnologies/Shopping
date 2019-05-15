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
import com.market.onshopping.model.itemforcategory;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private List<itemforcategory> moviesList;
    int totalamt = 0;
    Context context;
    String Name;
    private DatabaseHelper db;
    private int quantity = 1;
    private AdapterCallback callback;

    public MainAdapter(Class<? extends List> aClass) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView itemimage, cartminus, cartplus;
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


            addcartlinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    addAndminusLinear.setVisibility(View.VISIBLE);
                    addcartlinear.setVisibility(View.INVISIBLE);


                }
            });

        }

    }


    public MainAdapter(List<itemforcategory> moviesList, Context context, AdapterCallback adapterCallback) {
        this.moviesList = moviesList;
        this.context = context;
        this.callback = adapterCallback;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_adapter, parent, false);
        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final itemforcategory movie = moviesList.get(position);

        db.getAllNotes();

        List<Note> notesList = db.getAllNotes();
        int size = notesList.size();
        int i = 0;
        if (notesList != null) {
            if (i < size) {
                String LocalID = notesList.get(i).getNote();
                //String LocalID = String.valueOf(localID);
                String ServerId = movie.getItemID();
                if (LocalID.equals(ServerId)) {
                    holder.addAndminusLinear.setVisibility(View.VISIBLE);
                    holder.addcartlinear.setVisibility(View.INVISIBLE);
                    i++;

                    holder.name.setText(movie.getItemName());
                    holder.rupees.setText(movie.getDiscount());
                    holder.itweight.setText(movie.getItemID());
                    holder.itweight.setText(String.valueOf(movie.getAvailableQuantity()));
                    holder.itemamt.setText("Rs " + movie.getItemDescription());
                    holder.itemamt.setText("Rs " + movie.getItemPrice());
                    holder.quantityProductPage.setText("1");
                    // holder.cart_product.setText(String.valueOf(getItemCount()));

                    if (movie.getImage1() != null) {
                        Glide.with(context).load(movie.getItemIcon()).into(holder.itemimage);

           /* Glide.with(context).load(movie.getImage2()).into(holder.cartminus);
            Glide.with(context).load(movie.getImage3()).into(holder.cartplus);*/
                    }

                } else {
                    holder.name.setText(movie.getItemName());
                    holder.rupees.setText(movie.getDiscount());
                    holder.itweight.setText(movie.getItemID());
                    holder.itweight.setText(String.valueOf(movie.getAvailableQuantity()));
                    holder.itemamt.setText("Rs " + movie.getItemDescription());
                    holder.itemamt.setText("Rs " + movie.getItemPrice());
                    holder.quantityProductPage.setText("1");
                    // holder.cart_product.setText(String.valueOf(getItemCount()));

                    if (movie.getImage1() != null) {
                        Glide.with(context).load(movie.getItemIcon()).into(holder.itemimage);

           /* Glide.with(context).load(movie.getImage2()).into(holder.cartminus);
            Glide.with(context).load(movie.getImage3()).into(holder.cartplus);*/
                    }

                }
            } else {

                holder.name.setText(movie.getItemName());
                holder.rupees.setText(movie.getDiscount());
                holder.itweight.setText(movie.getItemID());
                holder.itweight.setText(String.valueOf(movie.getAvailableQuantity()));
                holder.itemamt.setText("Rs " + movie.getItemDescription());
                holder.itemamt.setText("Rs " + movie.getItemPrice());
                holder.quantityProductPage.setText("1");
                // holder.cart_product.setText(String.valueOf(getItemCount()));

                if (movie.getImage1() != null) {
                    Glide.with(context).load(movie.getItemIcon()).into(holder.itemimage);

           /* Glide.with(context).load(movie.getImage2()).into(holder.cartminus);
            Glide.with(context).load(movie.getImage3()).into(holder.cartplus);*/
                }

            }


        }


        holder.pluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (quantity < 500) {


                    String cartValue = holder.quantityProductPage.getText().toString();
                    String veg = holder.vegtotal.getText().toString();
                    int cartInt = Integer.parseInt(cartValue);
                    cartInt++;
                    holder.quantityProductPage.setText(String.valueOf(cartInt));
                    String itemID = movie.getItemID();
                    String ItemName = movie.getItemName();
                    String ItemDescription = movie.getItemDescription();
                    String ItemPrice = movie.getItemPrice();
                    String ItemIcon = movie.getItemIcon();
                    String itemCount = holder.quantityProductPage.getText().toString();
                    int id=Integer.parseInt(itemID);
                    db.updateNote(ItemPrice,id, itemCount, ItemName);

                    //  holder.quantityProductPage.setText(String.valueOf(quantity));
                }



               /* String cartValue = holder.cart_product.getText().toString();
                int cartInt = Integer.parseInt(cartValue);
                String total = movie.getItemPrice();
                float rs = Float.valueOf(total);
                float a = Float.valueOf(cartInt);

                if (cartInt >= 0) {
                    cartInt++;
                    holder.cart_product.setText(String.valueOf(cartInt));
                    double finalValue = a * rs;
                    holder.vegtotal.setText(String.valueOf(finalValue));
                }*/
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String cartValue = holder.quantityProductPage.getText().toString();
                String veg = holder.vegtotal.getText().toString();
                int cartInt = Integer.parseInt(cartValue);

                if (cartInt > 1) {
                    cartInt--;
                    holder.quantityProductPage.setText(String.valueOf(cartInt));


                    String itemID = movie.getItemID();
                    String ItemName = movie.getItemName();
                    String ItemDescription = movie.getItemDescription();
                    String ItemPrice = movie.getItemPrice();
                    String ItemIcon = movie.getItemIcon();
                    String itemCount = holder.quantityProductPage.getText().toString();
                    int id=Integer.parseInt(itemID);
                    db.updateNote(ItemPrice,id, itemCount, ItemName);
                    //holder.quantityProductPage.setText(String.valueOf(quantity));
                }

               /* String cartValue = holder.cart_product.getText().toString();
                String veg = holder.vegtotal.getText().toString();
                int cartInt = Integer.parseInt(cartValue);
                double tot = Double.valueOf(veg);
                String total = movie.getItemPrice();
                double cart = Double.valueOf(total);

                if (cartInt > 0) {
                    cartInt--;
                    holder.cart_product.setText(String.valueOf(cartInt));
                    double finalValue = tot - cart;
                    holder.vegtotal.setText(String.valueOf(finalValue));
                }*/
            }
        });

        holder.itemimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String finam = holder.vegtotal.getText().toString();
                Intent i = new Intent(context, CheckoutBillActivity.class);
                i.putExtra("itemid", movie.getItemID());
                i.putExtra("itemname", movie.getItemName());
                i.putExtra("itemdescription", movie.getItemDescription());
                i.putExtra("itemprice", movie.getItemPrice());
                i.putExtra("itemicon", movie.getItemIcon());
                i.putExtra("image1", movie.getImage1());
                i.putExtra("image2", movie.getImage2());
                i.putExtra("image3", movie.getImage3());
                i.putExtra("discount", movie.getDiscount());
                i.putExtra("minquan", movie.getMinQuantityValue());
                i.putExtra("scale", movie.getScale());
                i.putExtra("available", movie.getAvailableQuantity());
                i.putExtra("delivery", movie.getDeliveryAvailable());
                i.putExtra("fianlAmount", finam);


                context.startActivity(i);
            }
        });
        /*holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity.class);
                i.putExtra("serverid", movie.getItemDescription());
                i.putExtra("id", position);
                context.startActivity(i);
            }
        });*/

        holder.addcartlinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemID = movie.getItemID();
                String ItemName = movie.getItemName();
                String ItemDescription = movie.getItemDescription();
                String ItemPrice = movie.getItemPrice();
                String ItemIcon = movie.getItemIcon();
                String Image1 = movie.getImage1();
                String image2 = movie.getImage2();
                String Image3 = movie.getImage3();
                String Discount = movie.getDiscount();
                String MinQuantityValue = movie.getMinQuantityValue();
                String Scale = movie.getScale();
                Integer AvailableQuantity = movie.getAvailableQuantity();
                Boolean DeliveryAvailable = movie.getDeliveryAvailable();
                String itemCount = holder.quantityProductPage.getText().toString();
                holder.addAndminusLinear.setVisibility(View.VISIBLE);
                holder.addcartlinear.setVisibility(View.INVISIBLE);
                db.insertNote(itemID, ItemIcon, ItemName, itemCount, "",
                        ItemPrice, Discount, "", "");

                callback.onClickCallback(1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}