package app.aryasoft.fooddormunivercity.ApiConnection;

import java.util.ArrayList;

import app.aryasoft.fooddormunivercity.ApiModels.DormStudents;
import app.aryasoft.fooddormunivercity.Models.StudentsDormFoodStates;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DormApi
{

    @GET("api/")
    Call<Boolean> LoginDeliveryman(@Query("Username") String Username, @Query("Password") String Password);

    @GET("api/")
    Call<ArrayList<DormStudents>> GetDormStudents(@Query("skipItem") int skipItem, @Query("takeItem") int takeItem);

    @GET("api/")
    Call<Boolean> SubmitDormFoodStates(@Body ArrayList<StudentsDormFoodStates>StudentsDormFoodStates);
}
