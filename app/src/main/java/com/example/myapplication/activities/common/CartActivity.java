package com.example.myapplication.activities.common;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.adapters.CartAdapter;
import com.example.myapplication.models.Course;
import com.example.myapplication.utils.MockData;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        RecyclerView rvCart = findViewById(R.id.rvCart);
        TextView tvTotalPrice = findViewById(R.id.tvTotalPrice);
        Button btnCheckout = findViewById(R.id.btnCheckout);

        List<Course> cartItems = MockData.getCartItems();

        double total = 0;
        for (Course c : cartItems) {
            total += c.getDiscountPrice() > 0 ? c.getDiscountPrice() : c.getPrice();
        }
        tvTotalPrice.setText(String.format("$%.2f", total));

        CartAdapter adapter = new CartAdapter(cartItems);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.setAdapter(adapter);

        btnCheckout.setOnClickListener(v ->
                startActivity(new Intent(this, CheckoutActivity.class)));
    }
}
