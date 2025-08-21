package extended.ui.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import extended.ui.foodapp.adapters.MostPopularAdapters;
import extended.ui.foodapp.data.*;
import extended.ui.foodapp.databinding.FragmentHomeBinding;
import extended.ui.foodapp.viewModel.HomeViewModel;


public class HomeFragment extends Fragment {
private FragmentHomeBinding binding;
private HomeViewModel homeViewModel;
private Meal randomMeal;
private MostPopularAdapters mostPopularAdapters;
public static final String MEAL_ID="extended.ui.foodapp.idMeal";
public  static final String MEAL_NAME="extended.ui.foodapp.nameMeal";
public  static final String MEAL_THUMB="extended.ui.foodapp.thumbMeal";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel= new ViewModelProvider(this).get(HomeViewModel.class);
        mostPopularAdapters=new MostPopularAdapters();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
     prepareMostPopularItemsRecyclerView();
     homeViewModel.setRandomMeal();
     observeRandomMeal();
     randomMeal_clicked();
     homeViewModel.setCategoryMeals();
     observeCategoryLiveData();
    }

    private void prepareMostPopularItemsRecyclerView() {
        LinearLayoutManager horizontalLayout=new LinearLayoutManager(binding.getRoot().getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.recPopularMeals.setLayoutManager(horizontalLayout);
        binding.recPopularMeals.setAdapter(mostPopularAdapters);
    }

    private void observeCategoryLiveData() {
        homeViewModel.observerCategoryLiveData().observe(getViewLifecycleOwner(), new Observer<List<CategoryMeal>>() {
            @Override
            public void onChanged(List<CategoryMeal> categoryMeals) {
              mostPopularAdapters.setMeals((ArrayList<CategoryMeal>) categoryMeals);
            }
        });
    }

    private void observeRandomMeal() {
     homeViewModel.observerRandomMealLiveData().observe(getViewLifecycleOwner(), new Observer<Meal>() {
         @Override
         public void onChanged(Meal meal) {
             Glide.with(binding.getRoot())
                     .load(meal.getStrMealThumb())
                     .into(binding.imgRandomMeal);
              randomMeal=meal;
         }

     });
    }
    private  void randomMeal_clicked(){
     binding.randomMealCard.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent mealActivity=new Intent(v.getContext(),MealActivity.class);
             mealActivity.putExtra(MEAL_ID,randomMeal.getIdMeal());
             mealActivity.putExtra(MEAL_NAME,randomMeal.getStrMeal());
             mealActivity.putExtra(MEAL_THUMB,randomMeal.getStrMealThumb());
             v.getContext().startActivity(mealActivity);
         }
     });
    }
}