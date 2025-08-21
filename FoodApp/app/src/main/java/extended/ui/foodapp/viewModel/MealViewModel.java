package extended.ui.foodapp.viewModel;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import extended.ui.foodapp.data.Meal;
import extended.ui.foodapp.data.MealList;
import extended.ui.foodapp.retrofit.ApiManager;
import extended.ui.foodapp.retrofit.MealApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealViewModel extends ViewModel{
MutableLiveData <Meal> liveData =new MutableLiveData<>();
public void setMealDetails(String id){
    Call<MealList>mealListCall= ApiManager.getRetrofitInstance().create(MealApi.class).getMealDetails(id);
    mealListCall.enqueue(new Callback<MealList>() {
        @Override
        public void onResponse(Call<MealList> call, Response<MealList> response) {
            if(response.body()!=null){
                liveData.setValue(response.body().getMeals().get(0));
            }
        }

        @Override
        public void onFailure(Call<MealList> call, Throwable t) {
            Log.d(this.toString(),t.getMessage().toString());
        }
    });
}
//returning meal details
    public LiveData<Meal> getMealDetails(){
    return liveData;
    }
}

