package app.aryasoft.fooddormunivercity.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import app.aryasoft.fooddormunivercity.Fragments.DeliveryFragment;
import app.aryasoft.fooddormunivercity.Fragments.SearchStudentsFragment;
import app.aryasoft.fooddormunivercity.R;

public class LandActivity extends AppCompatActivity
{
    private int ActiveTabMenu = 0;
    private ImageView imgSearchStudents;
    private ImageView imgReportNotDelivered;
    private ImageView imgReportDelivered;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);
        initViews();
        initEvents();
    }

    private void initViews()
    {
        imgSearchStudents = findViewById(R.id.imgSearchStudents);
        imgReportNotDelivered = findViewById(R.id.imgReportNotDelivered);
        imgReportDelivered = findViewById(R.id.imgReportDelivered);
    }


    private void initEvents()
    {
        imgSearchStudents.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ActiveTabMenu == 1)
                {
                    return;
                }
                switchFragment(new SearchStudentsFragment());
                ActiveTabMenu = 1;
            }
        });
        imgReportNotDelivered.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ActiveTabMenu == 2)
                {
                    return;
                }
                switchFragment(new DeliveryFragment());
                ActiveTabMenu = 2;
            }
        });
        imgReportDelivered.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ActiveTabMenu == 3)
                {
                    return;
                }
                switchFragment(new DeliveryFragment());
                ActiveTabMenu = 3;
            }
        });
    }


    private void switchFragment(Fragment switchingFragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right).replace(R.id.contentPlaceHolder, switchingFragment);
        fragmentTransaction.commit();
    }
}
