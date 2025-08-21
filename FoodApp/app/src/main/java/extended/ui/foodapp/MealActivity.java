package extended.ui.foodapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.bumptech.glide.Glide;

import extended.ui.foodapp.data.Meal;
import extended.ui.foodapp.databinding.ActivityMealBinding;
import extended.ui.foodapp.viewModel.MealViewModel;

public class MealActivity extends AppCompatActivity {
    private ActivityMealBinding binding;
    private MealViewModel mealViewModel;
    private String mealID;
    private String mealName;
    private String mealThumb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMealBinding.inflate(getLayoutInflater());
        mealViewModel=new ViewModelProvider(this).get(MealViewModel.class);

        setContentView(binding.getRoot());
        getMealDetails(savedInstanceState);
        loadProgressBar();
        displayMealDetails();
        mealViewModel.setMealDetails(mealID);
        observeMealDetails();
    }

    private void loadProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.txtCategory.setVisibility(View.INVISIBLE);
        binding.txtLocation.setVisibility(View.INVISIBLE);
        binding.txtSteps.setVisibility(View.INVISIBLE);
        binding.imgCart.setVisibility(View.INVISIBLE);
        binding.floatingBtn.setVisibility(View.INVISIBLE);
    }

    private void observeMealDetails(){
        closeProgressBar();
        mealViewModel.getMealDetails().observe(this, new Observer<Meal>() {

            @Override
            public void onChanged(Meal meal) {
                binding.txtCategory.setText("Category: "+meal.getStrCategory());
                binding.txtLocation.setText("Origin: "+meal.getStrArea());
                binding.txtSteps.setText(meal.getStrInstructions());

            }

        });
    }

    private void closeProgressBar() {
        binding.progressBar.setVisibility(View.INVISIBLE);
        binding.txtCategory.setVisibility(View.VISIBLE);
        binding.txtLocation.setVisibility(View.VISIBLE);
        binding.txtSteps.setVisibility(View.VISIBLE);
        binding.imgCart.setVisibility(View.VISIBLE);
        binding.floatingBtn.setVisibility(View.VISIBLE);
    }

    private void displayMealDetails() {
        Glide.with(getApplicationContext())
                .load(mealThumb)
                .into(binding.imgMealDetails);
        binding.collapsingToolbar.setTitle(mealName);

    }

    private void getMealDetails(Bundle bundle) {

        bundle=getIntent().getExtras();
        mealID=bundle.getString(HomeFragment.MEAL_ID);
        mealName=bundle.getString(HomeFragment.MEAL_NAME);
        mealThumb=bundle.getString(HomeFragment.MEAL_THUMB);
    }

}