package app.aryasoft.fooddormunivercity.Modules.Utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

import app.aryasoft.fooddormunivercity.Models.SearchModel;
import app.aryasoft.fooddormunivercity.Models.StudentModel;

public class SearchModule
{
    private ArrayList<StudentModel> studentDataList;

    public SearchModule(ArrayList<StudentModel> studentDataList)
    {
        this.studentDataList = new ArrayList<>();
        this.studentDataList.addAll(studentDataList);
    }

    public ArrayList<StudentModel> SearchStudent(SearchModel searchParams)
    {
        ArrayList<StudentModel> resultStudentDataList = new ArrayList<>();
        //---------------------
        if (!(searchParams.StudentName.isEmpty()) || !(TextUtils.isEmpty(searchParams.StudentName)))
        {
            for (int i = 0; i < studentDataList.size(); ++i)
            {
                if (studentDataList.get(i).StudentName.trim().contains(searchParams.StudentName.trim()))
                {
                    resultStudentDataList.add(studentDataList.get(i));
                }
            }
        }
        if (!(searchParams.StudentFamily.isEmpty() || TextUtils.isEmpty(searchParams.StudentFamily)))
        {
            for (int i = 0; i < studentDataList.size(); ++i)
            {
                if (studentDataList.get(i).StudentFamily.trim().contains(searchParams.StudentFamily.trim()))
                {
                    resultStudentDataList.add(studentDataList.get(i));
                }
            }
        }
        if (!(searchParams.StudentCode.isEmpty() || TextUtils.isEmpty(searchParams.StudentCode)))
        {
            for (int i = 0; i < studentDataList.size(); ++i)
            {
                if (studentDataList.get(i).StudentCode.trim().contains(searchParams.StudentCode.trim()))
                {
                    resultStudentDataList.add(studentDataList.get(i));
                }
            }
        }
        //---------------------
        Log.i("res",resultStudentDataList.size()+"");
        return resultStudentDataList;
    }
}
