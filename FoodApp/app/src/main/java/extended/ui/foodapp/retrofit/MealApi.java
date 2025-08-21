package extended.ui.foodapp.retrofit;

import extended.ui.foodapp.data.CategoryMealList;
import extended.ui.foodapp.data.Meal;
import extended.ui.foodapp.data.MealList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MealApi {
    @GET("random.php")
    public Call<MealList> getRandomMeal();
    @GET("lookup.php?")
    public Call<MealList> getMealDetails(@Query("i") String id);
    @GET("filter.php")
    public Call<CategoryMealList> getCategoryList(@Query("c") String categoryName);
}
