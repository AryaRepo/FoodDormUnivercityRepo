package app.aryasoft.fooddormunivercity.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.aryasoft.fooddormunivercity.Modules.ModuleImplementation.DeliverymanLoginModule;
import app.aryasoft.fooddormunivercity.Modules.ModuleInterface.OnDeliverymanLoginListener;
import app.aryasoft.fooddormunivercity.Modules.ModuleInterface.OnResponseFailureListener;
import app.aryasoft.fooddormunivercity.R;

public class LoginActivity extends AppCompatActivity implements OnResponseFailureListener
{
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLoginDeliveryman;
    private DeliverymanLoginModule deliverymanLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        deliverymanLogin = new DeliverymanLoginModule();
        initViews();
        initEvents();
    }

    private void initViews()
    {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLoginDeliveryman = findViewById(R.id.btnLoginDeliveryman);
    }

    private void initEvents()
    {
        deliverymanLogin.setOnResponseFailureListener(this);
        btnLoginDeliveryman.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //login();
                startActivity(new Intent(LoginActivity.this, LandActivity.class));
            }
        });
    }

    private void login()
    {
        if (TextUtils.isEmpty(edtUsername.getText()) || TextUtils.isEmpty(edtPassword.getText()))
        {
            Toast.makeText(LoginActivity.this, "پیک عزیز لطفا اطلاعات ورود به حساب کاربری را وارد نمایید.", Toast.LENGTH_SHORT).show();
            return;
        }
        //----------------------------
        deliverymanLogin.Login(edtUsername.getText().toString(), edtPassword.getText().toString());
        deliverymanLogin.setOnDeliverymanLoginListener(new OnDeliverymanLoginListener()
        {
            @Override
            public void OnDeliverymanLogin(boolean loginState)
            {
                if (!loginState)
                {
                    Toast.makeText(LoginActivity.this, "پیک عزیز اطلاعات وارد شده صحیح نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(new Intent(LoginActivity.this, LandActivity.class));
            }
        });
    }

    @Override
    public void OnResponseFailure(Throwable throwableException)
    {
        Toast.makeText(this, "خطا در ارسال اطلاعات و احراز هویت", Toast.LENGTH_SHORT).show();
        Log.i("LoginActivity : ", throwableException.getMessage());
    }
}
