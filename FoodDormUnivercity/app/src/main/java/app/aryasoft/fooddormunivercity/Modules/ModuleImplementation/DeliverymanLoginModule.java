package app.aryasoft.fooddormunivercity.Modules.ModuleImplementation;
import android.support.annotation.NonNull;
import app.aryasoft.fooddormunivercity.ApiConnection.ApiServiceGenerator;
import app.aryasoft.fooddormunivercity.ApiConnection.DormApi;
import app.aryasoft.fooddormunivercity.Modules.ModuleInterface.OnDeliverymanLoginListener;
import app.aryasoft.fooddormunivercity.Modules.ModuleInterface.OnResponseFailureListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliverymanLoginModule
{
    private OnResponseFailureListener onResponseFailureListener;
    private OnDeliverymanLoginListener onDeliverymanLoginListener;
    //---------------------------------------------------------------------

    private DormApi dormApi;

    public DeliverymanLoginModule()
    {
        dormApi = ApiServiceGenerator.getApiService();
    }

    public void setOnDeliverymanLoginListener(OnDeliverymanLoginListener onDeliverymanLoginListener)
    {
        this.onDeliverymanLoginListener = onDeliverymanLoginListener;
    }

    public void setOnResponseFailureListener(OnResponseFailureListener onResponseFailureListener)
    {
        this.onResponseFailureListener = onResponseFailureListener;
    }

    public void Login(String userName, String password)
    {
        Call<Boolean> CallLoginDeliveryman =dormApi.LoginDeliveryman(userName,password);
        CallLoginDeliveryman.enqueue(new Callback<Boolean>()
        {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response)
            {
                onDeliverymanLoginListener.OnDeliverymanLogin(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t)
            {
                onResponseFailureListener.OnResponseFailure(t);
            }
        });
    }
}
