package app.aryasoft.fooddormunivercity.Modules.Utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import app.aryasoft.fooddormunivercity.Models.SearchModel;
import app.aryasoft.fooddormunivercity.Models.StudentModel;
import app.aryasoft.fooddormunivercity.Modules.ModuleInterface.OnSearchStudentListener;

public class SearchModule extends AsyncTask<SearchModel, Void, Void>
{
    private ArrayList<StudentModel> studentDataList;
    private OnSearchStudentListener onSearchStudentListener;
    private Context context;

    public SearchModule(ArrayList<StudentModel> studentDataList, Context context)
    {
        this.context = context;
        this.studentDataList = new ArrayList<>();
        this.studentDataList.addAll(studentDataList);
    }

    public void setOnSearchStudentListener(OnSearchStudentListener onSearchStudentListener)
    {
        this.onSearchStudentListener = onSearchStudentListener;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        Toast.makeText(context, "wait...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(SearchModel... searchModels)
    {
        SearchStudent(searchModels[0]);
        return null;
    }

    private void SearchStudent(SearchModel searchParams)
    {
        ArrayList<StudentModel> resultStudentDataList = new ArrayList<>();
        //---------------------
        if (searchParams.StudentName.length() != 0 && searchParams.StudentFamily.length() != 0 && searchParams.StudentCode.length() != 0)
        {

            for (int i = 0; i < studentDataList.size(); ++i)
            {
                if (studentDataList.get(i).StudentName.trim().contains(searchParams.StudentName.trim()) && studentDataList.get(i).StudentFamily.trim().contains(searchParams.StudentFamily.trim()) && studentDataList.get(i).StudentCode.trim().contains(searchParams.StudentCode.trim()))
                {
                    resultStudentDataList.add(studentDataList.get(i));
                }
            }
        }
        else if (searchParams.StudentName.length() != 0 && searchParams.StudentFamily.length() != 0)
        {
            for (int i = 0; i < studentDataList.size(); ++i)
            {
                if (studentDataList.get(i).StudentName.trim().contains(searchParams.StudentName.trim()) && studentDataList.get(i).StudentFamily.trim().contains(searchParams.StudentFamily.trim()))
                {
                    resultStudentDataList.add(studentDataList.get(i));
                }
            }
        }
        else
        {
            if (searchParams.StudentName.length() != 0)
            {
                for (int i = 0; i < studentDataList.size(); ++i)
                {
                    if (studentDataList.get(i).StudentName.trim().contains(searchParams.StudentName.trim()))
                    {
                        resultStudentDataList.add(studentDataList.get(i));
                    }
                }
            }
            else if (searchParams.StudentFamily.length() != 0)
            {
                for (int i = 0; i < studentDataList.size(); ++i)
                {
                    if (studentDataList.get(i).StudentFamily.trim().contains(searchParams.StudentFamily.trim()))
                    {
                        resultStudentDataList.add(studentDataList.get(i));
                    }
                }
            }
            else if (searchParams.StudentCode.length() != 0)
            {
                for (int i = 0; i < studentDataList.size(); ++i)
                {
                    if (studentDataList.get(i).StudentCode.trim().contains(searchParams.StudentCode.trim()))
                    {
                        resultStudentDataList.add(studentDataList.get(i));
                    }
                }
            }
        }
        //------------------------Clear Distinct Students
        for (int i = 0; i < resultStudentDataList.size(); ++i)
        {
            int count = 0;
            for (int j = 0; j < resultStudentDataList.size(); ++j)
            {
                count = 0;
                if (resultStudentDataList.get(i).StudentId == resultStudentDataList.get(j).StudentId)
                {
                    ++count;
                    if (count == 2)
                    {
                        resultStudentDataList.remove(i);
                    }
                }
            }
        }
        //---------------------
        Log.i("res", resultStudentDataList.size() + "");
        onSearchStudentListener.OnSearchStudent(resultStudentDataList);
    }


}
