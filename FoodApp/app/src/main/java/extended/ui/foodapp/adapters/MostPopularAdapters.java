package extended.ui.foodapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import extended.ui.foodapp.data.CategoryMeal;
import extended.ui.foodapp.data.CategoryMealList;
import extended.ui.foodapp.databinding.PopularItemsBinding;

public class MostPopularAdapters extends RecyclerView.Adapter<MostPopularAdapters.PopularMealViewHolder> {
    private ArrayList<CategoryMeal> meals=new ArrayList<>();
    private  PopularItemsBinding binding;
    public void setMeals(ArrayList<CategoryMeal> meals){
    this.meals=meals;
    notifyDataSetChanged();
   }
    public static  class PopularMealViewHolder extends RecyclerView.ViewHolder{

        public PopularMealViewHolder(@NonNull PopularItemsBinding itemView) {
            super(itemView.getRoot());
        }
    }

    @NonNull
    @Override
    public PopularMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding=PopularItemsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PopularMealViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularMealViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(meals.get(position))
                .into(binding.popularMealItems);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
