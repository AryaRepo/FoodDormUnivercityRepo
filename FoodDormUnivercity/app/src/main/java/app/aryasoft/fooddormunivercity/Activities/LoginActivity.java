package app.aryasoft.fooddormunivercity.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import app.aryasoft.fooddormunivercity.DbManager.DataAccessLayer;
import app.aryasoft.fooddormunivercity.DbManager.DataAccessObject;
import app.aryasoft.fooddormunivercity.DbManager.DbModels.DeliveredReservationModel;
import app.aryasoft.fooddormunivercity.R;

public class LoginActivity extends AppCompatActivity
{

    private Button btnLoginDeliveryman;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLoginDeliveryman=findViewById(R.id.btnLoginDeliveryman);
        btnLoginDeliveryman.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(LoginActivity.this, "ورود شما با موفقیت انجام شد.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,LandActivity.class));
            }
        });
    }


}
