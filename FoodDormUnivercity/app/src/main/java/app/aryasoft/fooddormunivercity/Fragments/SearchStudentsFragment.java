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
import app.aryasoft.fooddormunivercity.R;

public class SearchStudentsFragment extends Fragment
{
    private Context fragmentContext;
    private RecyclerView recyclerSearchDormMembers;
    private DormMembersAdapter recyclerDormMembersAdapter;
    private LinearLayoutManager recyclerDormMembersLayoutManager;

    public SearchStudentsFragment()
    {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        fragmentContext = view.getContext();
        initViews(view);
        initEvents();
    }

    private void initViews(View view)
    {
        recyclerSearchDormMembers = view.findViewById(R.id.recyclerSearchDormMembers);
        //---
        recyclerDormMembersAdapter =new DormMembersAdapter(fragmentContext);
        recyclerDormMembersLayoutManager = new LinearLayoutManager(fragmentContext, LinearLayoutManager.VERTICAL, false);
        recyclerSearchDormMembers.setLayoutManager(recyclerDormMembersLayoutManager);
        recyclerSearchDormMembers.setAdapter(recyclerDormMembersAdapter);
    }

    private void initEvents()
    {

    }
}
