package com.market.onshopping;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.market.onshopping.INterface.AdapterCallback;
import com.market.onshopping.activity.CheckoutBillActivity;
import com.market.onshopping.adapter.MainAdapter;
import com.market.onshopping.model.DatabaseHelper;
import com.market.onshopping.model.itemforcategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterCallback {
    private RecyclerView recyclerView;
    private MainAdapter mAdapter;
    Button placeorder;
    ImageView backbutton;
    String serverid, dis;
    RelativeLayout shopcart;
    RequestQueue MyRequestQueue;
    private DatabaseHelper db;
    AdapterCallback adapterCallback;
    TextView txtcount;
    ImageView imageCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        txtcount = (TextView) findViewById(R.id.count);
        imageCart = (ImageView) findViewById(R.id.cart);
        db = new DatabaseHelper(getApplicationContext());
        adapterCallback = MainActivity.this;
        MyRequestQueue = Volley.newRequestQueue(getApplicationContext());
        getBanner("1");

        db.getAllNotes();
        Log.i("dpnotes", db.getAllNotes().toString());
        int count = db.getAllNotes().size();
        txtcount.setText(String.valueOf(count));


        imageCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent shopcart=new Intent(getApplicationContext(), CheckoutBillActivity.class);
                    startActivity(shopcart);
                }
        });
        txtcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shopcart = new Intent(getApplicationContext(), CheckoutBillActivity.class);
                startActivity(shopcart);
            }
        });


    }

    @Override
    public void onClick(View v) {

    }

    private void getBanner(String serverid) {

        final String url = "http://mymarket.napeans.com/api/Item/ItemsForCategory";

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.i("ResponceMesage", response.toString());

                String jsonString = response;
                JSONArray jsonArray = null;
                try {
                    List<itemforcategory> userList = new ArrayList<>();
                    jsonArray = new JSONArray(jsonString);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jobj = jsonArray.getJSONObject(i);

                        String id = jobj.getString("ItemID");
                        String itemname = jobj.getString("ItemName");
                        String itemprice = jobj.getString("ItemPrice");
                        String itemicon = jobj.getString("ItemIcon");
                        String image1 = jobj.getString("Image1");
                        String image2 = jobj.getString("Image2");
                        String image3 = jobj.getString("Image3");
                        String Discount = jobj.getString("Discount");
                        String minquantityvalue = jobj.getString("MinQuantityValue");
                        String availabilequantity = jobj.getString("AvailabileQuantity");
                        String deliveryavailable = jobj.getString("DeliveryAvailable");


                        itemforcategory jobj2 = new itemforcategory();
                        jobj2.setItemID(id);
                        jobj2.setItemName(itemname);
                        jobj2.setItemPrice(itemprice);
                        jobj2.setItemIcon(itemicon);
                        jobj2.setImage1(image1);
                        jobj2.setImage2(image2);
                        jobj2.setImage3(image3);
                        jobj2.setDiscount(Discount);
                        jobj2.setMinQuantityValue(minquantityvalue);
                        jobj2.setAvailableQuantity(Integer.valueOf(availabilequantity));
                        jobj2.setDeliveryAvailable(Boolean.valueOf(deliveryavailable));
                        userList.add(jobj2);


                        mAdapter = new MainAdapter(userList, getApplicationContext(), adapterCallback);
                    }


                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                    recyclerView.setAdapter(mAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ErrorMessage", error.toString());
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {


                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("CategoryID", "1");
                MyData.put("CategoryName", "Value");
                MyData.put("IconUrl", "Value");
                return MyData;
            }
        };


        MyRequestQueue.add(MyStringRequest);

    }

    @Override
    public void onClickCallback(int itemModel) {
        String cartValue = txtcount.getText().toString();
        int getCartValue = Integer.parseInt(cartValue);
        int finalCartvalue = itemModel + getCartValue;

        txtcount.setText(String.valueOf(finalCartvalue));
        //Toast.makeText(this, itemModel, Toast.LENGTH_SHORT).show();
    }
}

