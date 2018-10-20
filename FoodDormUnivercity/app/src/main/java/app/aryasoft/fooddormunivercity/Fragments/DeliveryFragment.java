package app.aryasoft.fooddormunivercity.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.aryasoft.fooddormunivercity.Adapters.DormMembersAdapter;
import app.aryasoft.fooddormunivercity.DbManager.DataAccessLayer;
import app.aryasoft.fooddormunivercity.DbManager.DataAccessObject;
import app.aryasoft.fooddormunivercity.R;

public class DeliveryFragment extends Fragment
{
    private Context fragmentContext;
    private RecyclerView recyclerDormMembers;
    private DormMembersAdapter recyclerDormMembersAdapter;
    private LinearLayoutManager recyclerDormMembersLayoutManager;
    private DataAccessObject dbContext;

    public DeliveryFragment()
    {
        //1->delivered or 2->not delivered
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_delivery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        fragmentContext = view.getContext();
        dbContext = new DataAccessObject(new DataAccessLayer(fragmentContext.getApplicationContext()));
        initViews(view);
        initEvents();
    }

    private void initViews(View view)
    {
        recyclerDormMembers = view.findViewById(R.id.recyclerDormMembers);
        recyclerDormMembersAdapter = new DormMembersAdapter(fragmentContext, dbContext);
        recyclerDormMembersLayoutManager = new LinearLayoutManager(fragmentContext, LinearLayoutManager.VERTICAL, false);
        recyclerDormMembers.setLayoutManager(recyclerDormMembersLayoutManager);
        recyclerDormMembers.setAdapter(recyclerDormMembersAdapter);
        int deliveryType = getArguments().getInt("deliveryType");
        loadStudentDataByDeliveryType(deliveryType);
    }


    private void initEvents()
    {

    }

    private void loadStudentDataByDeliveryType(int deliveryType)
    {
        switch (deliveryType)
        {
            case 1:
                //load delivered
                recyclerDormMembersAdapter.addStudentDataList(dbContext.getDeliveredReservation());
                break;
            case 2:
                //load not delivered
               // recyclerDormMembersAdapter.addStudentDataList();
                break;

        }
    }
}
