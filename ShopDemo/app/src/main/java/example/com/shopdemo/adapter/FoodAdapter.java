package example.com.shopdemo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import example.com.shopdemo.Food;
import example.com.shopdemo.R;

/**
 * Created by rhm on 2017/4/11.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    List<Food> list = new ArrayList<Food>();
    Context mcontext;

    public FoodAdapter(List<Food> list) {
        this.list = list;
    }

    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mcontext == null) {
            mcontext = parent.getContext();
        }
        View view = LayoutInflater.from(mcontext).inflate(R.layout.food_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //绑定
    @Override
    public void onBindViewHolder(FoodAdapter.ViewHolder holder, int position) {
        Food food = list.get(position);//获得food
        holder.foodName.setText(food.getFoodName());
        holder.foodPrice.setText("￥"+food.getFoodPrice());
        //由于图片很大，所以使用了glide来接收图片
        Glide.with(mcontext).load(food.getImageId()).into(holder.foodImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        CircleImageView foodImage;
        TextView foodName;
        TextView foodPrice;


        //初始化操作
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            foodImage = (CircleImageView) itemView.findViewById(R.id.food_image);
            foodName = (TextView) itemView.findViewById(R.id.food_name);
            foodPrice = (TextView) itemView.findViewById(R.id.food_price);
        }
    }
}
