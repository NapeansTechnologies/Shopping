package com.market.onshopping.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.market.onshopping.MainActivity;
import com.market.onshopping.R;
import com.market.onshopping.adapter.CheckoutAdapter;
import com.market.onshopping.model.DatabaseHelper;
import com.market.onshopping.model.Note;
import com.market.onshopping.utils.PrefManager;

import java.util.ArrayList;
import java.util.List;

public class CheckoutBillActivity extends AppCompatActivity {
    private CheckoutAdapter mAdapter;
    View view;
    private List<Note> notesList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    TextView ContinueShopping, item_total, shipping, orderTotal, checkout;
    private DatabaseHelper db;
    LinearLayout empty_LinearLayout, notEmptyLinearLayout;
    RelativeLayout relativeLayoutBottom;
    TextView txtCheckout;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_bill);

        recyclerView = findViewById(R.id.recyclerview);
        empty_LinearLayout = (LinearLayout) findViewById(R.id.empty_hide);
        notEmptyLinearLayout = (LinearLayout) findViewById(R.id.activity_cart_list);
        relativeLayoutBottom = (RelativeLayout) findViewById(R.id.rel_bottom);
        ContinueShopping = (TextView) findViewById(R.id.continue_shopping);
        txtCheckout = (TextView) findViewById(R.id.checkout);

        ContinueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shopcart = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(shopcart);
            }
        });
        db = new DatabaseHelper(getApplicationContext());
        notesList.addAll(db.getAllNotes());
        if (notesList.size() == 0) {
            empty_LinearLayout.setVisibility(View.VISIBLE);
            empty_LinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            notEmptyLinearLayout.setVisibility(View.GONE);
            relativeLayoutBottom.setVisibility(View.GONE);

        } else {
            int count = db.getAllNotes().size();
            mAdapter = new CheckoutAdapter(notesList, getApplicationContext());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext().getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(0), true));
            recyclerView.setAdapter(mAdapter);

            for (int i = 0; i < notesList.size(); i++) {
                String idd = String.valueOf(notesList.get(i).getNote().toString());
                double a = Double.valueOf(notesList.get(i).getAdult_amount());
                double adultcount = Double.valueOf(notesList.get(i).getItemCount());
                /*double ch = Double.valueOf(notesList.get(i).getChild_amount());
                double childcount = Double.valueOf(notesList.get(i).getChild());
                String date = String.valueOf(notesList.get(i).getDatee().toString());
                Log.i("dateInLocaldatabase", date);
                double mul = (a * adultcount) + (ch * childcount);*/


            }

        }
        PrefManager prefManager = new PrefManager(getApplicationContext());
        userId = prefManager.getEmail();
        txtCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId.equals("")) {
                    Intent intent = new Intent(CheckoutBillActivity.this, LoginActivity.class);
                    startActivity(intent);
                    notesList.clear();

                }
            }
        });

    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) *
                // spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /
                // spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(CheckoutBillActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
