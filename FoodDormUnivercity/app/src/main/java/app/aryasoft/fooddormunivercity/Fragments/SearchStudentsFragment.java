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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.aryasoft.fooddormunivercity.Adapters.DormMembersAdapter;
import app.aryasoft.fooddormunivercity.Models.SearchModel;
import app.aryasoft.fooddormunivercity.Models.StudentModel;
import app.aryasoft.fooddormunivercity.Modules.ModuleInterface.OnSearchStudentListener;
import app.aryasoft.fooddormunivercity.Modules.Utils.SearchModule;
import app.aryasoft.fooddormunivercity.R;

public class SearchStudentsFragment extends Fragment
{
    private LinearLayout linNoData;
    private LinearLayout linLoading;
    private ImageView imgLoading;
    private EditText edtStudentNameSearch;
    private EditText edtStudentFamilySearch;
    private EditText edtStudentCodeSearch;
    private Button btnSearchStudent;
    //-----------------------------
    private Context fragmentContext;
    private RecyclerView recyclerSearchDormMembers;
    private DormMembersAdapter recyclerDormMembersAdapter;
    private LinearLayoutManager recyclerDormMembersLayoutManager;
    private ArrayList<StudentModel> resultStudentDataList;

    //---------------
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
        linNoData = view.findViewById(R.id.linNoData);
        linLoading = view.findViewById(R.id.linLoading);
        imgLoading = view.findViewById(R.id.imgLoading);
        Glide.with(this).load(R.drawable.data_loader).into(imgLoading);
        recyclerSearchDormMembers = view.findViewById(R.id.recyclerSearchDormMembers);
        edtStudentNameSearch = view.findViewById(R.id.edtStudentNameSearch);
        edtStudentFamilySearch = view.findViewById(R.id.edtStudentFamilySearch);
        edtStudentCodeSearch = view.findViewById(R.id.edtStudentCodeSearch);
        btnSearchStudent = view.findViewById(R.id.btnSearchStudent);
        //---
        recyclerDormMembersAdapter = new DormMembersAdapter(fragmentContext);
        recyclerDormMembersLayoutManager = new LinearLayoutManager(fragmentContext, LinearLayoutManager.VERTICAL, false);
        recyclerSearchDormMembers.setLayoutManager(recyclerDormMembersLayoutManager);
        recyclerSearchDormMembers.setAdapter(recyclerDormMembersAdapter);
        btnSearchStudent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                resultStudentDataList = new ArrayList<>();
                recyclerDormMembersAdapter.clearStudentDataList();
                SearchModule searchModule = new SearchModule(fillDummyData());
                SearchModel searchModel = new SearchModel();
                searchModel.StudentName = edtStudentNameSearch.getText().toString();
                searchModel.StudentFamily = edtStudentFamilySearch.getText().toString();
                searchModel.StudentCode = edtStudentCodeSearch.getText().toString();
                //-----------------
                linLoading.setVisibility(View.VISIBLE);

                searchModule.setOnSearchStudentListener(new OnSearchStudentListener()
                {
                    @Override
                    public void OnSearchStudent(ArrayList<StudentModel> resultStudentData)
                    {

                        resultStudentDataList = resultStudentData;
                        getActivity().runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                linLoading.setVisibility(View.GONE);
                                if (resultStudentDataList.size() == 0)
                                {
                                    recyclerSearchDormMembers.setVisibility(View.GONE);
                                    linNoData.setVisibility(View.VISIBLE);
                                    return;
                                }
                                recyclerSearchDormMembers.setVisibility(View.VISIBLE);
                                linNoData.setVisibility(View.GONE);
                                recyclerDormMembersAdapter.addStudentDataList(resultStudentDataList);
                            }
                        });

                    }
                });
                searchModule.execute(searchModel);
            }
        });
    }

    private void initEvents()
    {

    }

    private ArrayList<StudentModel> fillDummyData()
    {

        ArrayList<StudentModel> students = new ArrayList<>();
        //for (int i = 1; i <=17000; ++i)
        //{
            students.add(new StudentModel(1, "مهدی", "عباسی", "9522104516445", "کباب کوبیده سلطانی"));
            students.add(new StudentModel(2, "احمد", "غیاثوند", "966810378484", "جوجه کباب"));
            students.add(new StudentModel(3, "رضا", "سعیدی نیا", "952214361464", "کباب برگ"));
            students.add(new StudentModel(4, "سعید", "ترکمن", "845610331464", "برنج و خورش قرمه سبزی"));
            students.add(new StudentModel(5, "مرتضی", "احمدوند", "652510331144", "سبزی پلو با ماهی"));
            students.add(new StudentModel(6, "مسعود", "طلایی", "951110531494", "کشمش پلو"));
            students.add(new StudentModel(7, "محمد", "غیاثوند", "951110531494", "برنج و تن ماهی"));
            students.add(new StudentModel(8, "جواد", "امینی", "972210331464", "کباب کوبیده"));
            students.add(new StudentModel(9, "محمد امین", "چهاردولی", "931560331431", "ماکارونی"));
            students.add(new StudentModel(10, "سیاوش", "قمری", "955312331464", "برنج و خورش قیمه"));
            students.add(new StudentModel(11, "نگار", "شاملو", "856210331454", "عدس پلو"));
            students.add(new StudentModel(12, "سارا", "خلخالی", "9734403354698", "کباب کوبیده"));
            students.add(new StudentModel(13, "میثم", "مرادی", "941350331524", "سبزی پلو"));
            students.add(new StudentModel(14, "مجید", "طهماسبی", "954210331484", "آبگوشت"));
            students.add(new StudentModel(15, "سلمان", "راد", "922810338462", "برنج و مرغ"));
            students.add(new StudentModel(16, "فرید", "میرزایی", "912210331464", "چلو ماهی"));
            students.add(new StudentModel(17, "علی", "احمدی", "891573511467", "ماهی سالمون"));
            students.add(new StudentModel(18, "جاسم", "ترکاشوند", "955510481424", "میگو"));
            students.add(new StudentModel(19, "حبیب", "مرادی", "932815331464", "خرچنگ"));
            students.add(new StudentModel(20, "امیر", "عباسی", "988210331464", "کباب کوبیده"));
            students.add(new StudentModel(21, "محسن", "گودرزی", "942210331264", "کباب کوبیده"));
            students.add(new StudentModel(22, "حسن", "مصطفوی", "952210231262", "کباب کوبیده"));
            students.add(new StudentModel(23, "حسین", "صدر", "952210231263", "سالاد الویه"));
            students.add(new StudentModel(24, "فاطمه", "شاملو", "867414334425", "استانبلی با ماست"));
            students.add(new StudentModel(25, "داوود", "جهانشاهی", "954516336864", "کباب کوبیده"));
            students.add(new StudentModel(26, "زهرا", "بیاتی", "952213831469", "عدس پلو"));
            students.add(new StudentModel(27, "بهمن", "بابایی", "942210331789", "برنچ و مرغ"));
            students.add(new StudentModel(28, "بابک", "سامنی", "952210331464", "سبزی پلو"));
            students.add(new StudentModel(29, "سمیه", "فاضلی", "952610731869", "چلو گوشت"));
            students.add(new StudentModel(30, "سهند", "غیاثوند", "952214331485", "ماکارونی"));
       // }
        return students;

    }
}
