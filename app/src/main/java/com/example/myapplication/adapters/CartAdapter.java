package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.models.Course;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Course> cartList;

    public CartAdapter(List<Course> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Course course = cartList.get(position);

        holder.tvTitle.setText(course.getTitle());

        com.example.myapplication.models.User instructor =
                com.example.myapplication.utils.MockData.getUserById(course.getInstructorId());
        holder.tvInstructor.setText(instructor != null ? instructor.getFullName() : "Unknown Instructor");

        double price = course.getDiscountPrice() > 0 ? course.getDiscountPrice() : course.getPrice();
        holder.tvPrice.setText(String.format("$%.2f", price));
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvInstructor, tvPrice;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvCourseTitle);
            tvInstructor = itemView.findViewById(R.id.tvInstructor);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}