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
import app.aryasoft.fooddormunivercity.DbManager.DataAccessLayer;
import app.aryasoft.fooddormunivercity.DbManager.DataAccessObject;
import app.aryasoft.fooddormunivercity.Models.SearchModel;
import app.aryasoft.fooddormunivercity.Models.Student;
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
    private DataAccessObject dbContext;
    private Context fragmentContext;
    private RecyclerView recyclerSearchDormMembers;
    private DormMembersAdapter recyclerDormMembersAdapter;
    private LinearLayoutManager recyclerDormMembersLayoutManager;
    private ArrayList<Student> resultStudentDataList;

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
        dbContext = new DataAccessObject(new DataAccessLayer(fragmentContext.getApplicationContext()));
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
        recyclerDormMembersAdapter = new DormMembersAdapter(fragmentContext, dbContext);
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
                    public void OnSearchStudent(ArrayList<Student> resultStudentData)
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
                                compareStudentsData(dbContext);
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

    private void compareStudentsData(DataAccessObject dbContext)
    {
        ArrayList<Student> deliveredStudentData = dbContext.getDeliveredReservation();
        if (deliveredStudentData.size() != 0)
        {
            for (int i = 0; i < deliveredStudentData.size(); ++i)
            {
                for (int j = 0; j < resultStudentDataList.size(); ++j)
                {
                    if (deliveredStudentData.get(i).StudentId == resultStudentDataList.get(j).StudentId)
                    {
                        Student student = resultStudentDataList.get(j);
                        student.ReserveState = 1;
                        resultStudentDataList.set(j, student);
                    }
                }

            }
        }
    }

    private ArrayList<Student> fillDummyData()
    {

        ArrayList<Student> students = new ArrayList<>();
        //for (int i = 1; i <=17000; ++i)
        //{
        students.add(new Student(1, "مهدی", "عباسی", "9522104516445", "کباب کوبیده سلطانی"));
        students.add(new Student(2, "احمد", "غیاثوند", "966810378484", "جوجه کباب"));
        students.add(new Student(3, "رضا", "سعیدی نیا", "952214361464", "کباب برگ"));
        students.add(new Student(4, "سعید", "ترکمن", "845610331464", "برنج و خورش قرمه سبزی"));
        students.add(new Student(5, "مرتضی", "احمدوند", "652510331144", "سبزی پلو با ماهی"));
        students.add(new Student(6, "مسعود", "طلایی", "951110531494", "کشمش پلو"));
        students.add(new Student(7, "محمد", "غیاثوند", "951110531494", "برنج و تن ماهی"));
        students.add(new Student(8, "جواد", "امینی", "972210331464", "کباب کوبیده"));
        students.add(new Student(9, "محمد امین", "چهاردولی", "931560331431", "ماکارونی"));
        students.add(new Student(10, "سیاوش", "قمری", "955312331464", "برنج و خورش قیمه"));
        students.add(new Student(11, "نگار", "شاملو", "856210331454", "عدس پلو"));
        students.add(new Student(12, "سارا", "خلخالی", "9734403354698", "کباب کوبیده"));
        students.add(new Student(13, "میثم", "مرادی", "941350331524", "سبزی پلو"));
        students.add(new Student(14, "مجید", "طهماسبی", "954210331484", "آبگوشت"));
        students.add(new Student(15, "سلمان", "راد", "922810338462", "برنج و مرغ"));
        students.add(new Student(16, "فرید", "میرزایی", "912210331464", "چلو ماهی"));
        students.add(new Student(17, "علی", "احمدی", "891573511467", "ماهی سالمون"));
        students.add(new Student(18, "جاسم", "ترکاشوند", "955510481424", "میگو"));
        students.add(new Student(19, "حبیب", "مرادی", "932815331464", "خرچنگ"));
        students.add(new Student(20, "امیر", "عباسی", "988210331464", "کباب کوبیده"));
        students.add(new Student(21, "محسن", "گودرزی", "942210331264", "کباب کوبیده"));
        students.add(new Student(22, "حسن", "مصطفوی", "952210231262", "کباب کوبیده"));
        students.add(new Student(23, "حسین", "صدر", "952210231263", "سالاد الویه"));
        students.add(new Student(24, "فاطمه", "شاملو", "867414334425", "استانبلی با ماست"));
        students.add(new Student(25, "داوود", "جهانشاهی", "954516336864", "کباب کوبیده"));
        students.add(new Student(26, "زهرا", "بیاتی", "952213831469", "عدس پلو"));
        students.add(new Student(27, "بهمن", "بابایی", "942210331789", "برنچ و مرغ"));
        students.add(new Student(28, "بابک", "سامنی", "952210331464", "سبزی پلو"));
        students.add(new Student(29, "سمیه", "فاضلی", "952610731869", "چلو گوشت"));
        students.add(new Student(30, "سهند", "غیاثوند", "952214331485", "ماکارونی"));
        // }
        return students;

    }
}
