package app.aryasoft.fooddormunivercity.Modules.Utils;

import android.os.AsyncTask;

import java.util.ArrayList;

import app.aryasoft.fooddormunivercity.Models.SearchModel;
import app.aryasoft.fooddormunivercity.Models.Student;
import app.aryasoft.fooddormunivercity.Modules.ModuleInterface.OnSearchStudentListener;

public class SearchModule extends AsyncTask<SearchModel, Void, Void>
{
    private ArrayList<Student> studentDataList;
    private OnSearchStudentListener onSearchStudentListener;

    public SearchModule(ArrayList<Student> studentDataList)
    {
        this.studentDataList = new ArrayList<>();
        this.studentDataList.addAll(studentDataList);
    }

    public void setOnSearchStudentListener(OnSearchStudentListener onSearchStudentListener)
    {
        this.onSearchStudentListener = onSearchStudentListener;
    }


    @Override
    protected Void doInBackground(SearchModel... searchModels)
    {
        SearchStudent(searchModels[0]);
        return null;
    }

    private void SearchStudent(SearchModel searchParams)
    {
        ArrayList<Student> resultStudentDataList = new ArrayList<>();
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
        int studentIndex = 0;
        while (studentIndex < resultStudentDataList.size() - 1)
        {

            if (resultStudentDataList.get(studentIndex).StudentId == resultStudentDataList.get(studentIndex + 1).StudentId)
            {
                resultStudentDataList.remove(studentIndex);
            }
            else
            {
                studentIndex++;
            }
        }
        //---------------------
        onSearchStudentListener.OnSearchStudent(resultStudentDataList);
    }


}
