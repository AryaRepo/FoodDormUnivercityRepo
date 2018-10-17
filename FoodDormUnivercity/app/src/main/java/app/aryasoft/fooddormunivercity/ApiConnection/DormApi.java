package app.aryasoft.fooddormunivercity.ApiConnection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DormApi
{

    @GET("api/")
    Call<Boolean> LoginDeliveryman(@Query("Username") String Username, @Query("Password") String Password);
}
