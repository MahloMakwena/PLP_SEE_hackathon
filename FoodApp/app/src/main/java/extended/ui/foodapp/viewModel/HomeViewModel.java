package extended.ui.foodapp.viewModel;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import extended.ui.foodapp.data.CategoryMeal;
import extended.ui.foodapp.data.CategoryMealList;
import extended.ui.foodapp.data.Meal;
import extended.ui.foodapp.data.MealList;
import extended.ui.foodapp.retrofit.ApiManager;
import extended.ui.foodapp.retrofit.MealApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<Meal> randomMealData=new MutableLiveData<Meal>();
    private MutableLiveData<List<CategoryMeal>> catMeals=new MutableLiveData<>();
    public void setRandomMeal(){
        Call<MealList> call = ApiManager.getRetrofitInstance().create(MealApi.class).getRandomMeal();
        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(Call<MealList> call, Response<MealList> response) {
                if(response.body() != null && response.isSuccessful()){
                    Meal randomMeal = response.body().getMeals().get(0);
                    randomMealData.setValue(randomMeal);
                }
                else
                {
                    return;
                }
            }

            @Override
            public void onFailure(Call<MealList> call, Throwable t) {
                Log.d("HomeFragment",t.getMessage().toString());
            }

        });
    }

    public void setCategoryMeals() {
        Call<CategoryMealList>categoryMealListCall=ApiManager.getRetrofitInstance().create(MealApi.class).getCategoryList("Seafood");
        categoryMealListCall.enqueue(new Callback<CategoryMealList>() {
            @Override
            public void onResponse(Call<CategoryMealList> call, Response<CategoryMealList> response) {
                if(response!=null){
                  catMeals.setValue(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<CategoryMealList> call, Throwable t) {
                Log.d("HomeFragment",t.getMessage().toString());
            }
        });
    }

    public LiveData<List<CategoryMeal>> observerCategoryLiveData() {
        return catMeals;
    }

    public LiveData<Meal> observerRandomMealLiveData(){
        return randomMealData;
}
}
