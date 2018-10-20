package app.aryasoft.fooddormunivercity.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import app.aryasoft.fooddormunivercity.Fragments.DeliveryFragment;
import app.aryasoft.fooddormunivercity.Fragments.SearchStudentsFragment;
import app.aryasoft.fooddormunivercity.R;

public class LandActivity extends AppCompatActivity
{
    private int ActiveTabMenu = 0;
    private LinearLayout btnSearchStudents;
    private LinearLayout btnReportNotDelivered;
    private LinearLayout btnReportDelivered;
    private LinearLayout btnRefresh;
    private LinearLayout btnSubmitChanges;

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
        btnSearchStudents = findViewById(R.id.btnSearchStudents);
        btnReportNotDelivered = findViewById(R.id.btnReportNotDelivered);
        btnReportDelivered = findViewById(R.id.btnReportDelivered);
        btnRefresh = findViewById(R.id.btnRefresh);
        btnSubmitChanges = findViewById(R.id.btnSubmitChanges);
    }


    private void initEvents()
    {
        btnSearchStudents.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ActiveTabMenu == 1)
                {
                    return;
                }
                ActiveTabMenu = 1;
                switchFragment(new SearchStudentsFragment(), v, btnSubmitChanges, btnRefresh, btnReportNotDelivered, btnReportDelivered);

            }
        });
        btnReportNotDelivered.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ActiveTabMenu == 2)
                {
                    return;
                }
                ActiveTabMenu = 2;
                switchFragment(new SearchStudentsFragment(), v, btnSubmitChanges, btnRefresh, btnSearchStudents, btnReportDelivered);
            }
        });
        btnReportDelivered.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ActiveTabMenu == 3)
                {
                    return;
                }
                ActiveTabMenu = 3;
                switchFragment(new SearchStudentsFragment(), v, btnSubmitChanges, btnRefresh, btnSearchStudents, btnReportNotDelivered);
            }
        });
        btnRefresh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ActiveTabMenu == 4)
                {
                    return;
                }
                ActiveTabMenu = 4;
                switchFragment(new SearchStudentsFragment(), v, btnSearchStudents, btnSubmitChanges, btnReportDelivered, btnReportNotDelivered);
            }
        });
        btnSubmitChanges.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ActiveTabMenu == 5)
                {
                    return;
                }
                ActiveTabMenu = 5;
                switchFragment(new SearchStudentsFragment(), v, btnSearchStudents, btnRefresh, btnReportDelivered, btnReportNotDelivered);
            }
        });
    }


    private void switchFragment(Fragment switchingFragment, View viewToActive, View... viewsToDeActive)
    {
        viewToActive.setBackgroundColor(Color.parseColor("#0D47A1"));
        for (View aViewsToDeActive : viewsToDeActive)
        {
            aViewsToDeActive.setBackgroundColor(Color.TRANSPARENT);
        }
        //------------------
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right).replace(R.id.contentPlaceHolder, switchingFragment);
        fragmentTransaction.commit();
        //-------------------
    }
}
