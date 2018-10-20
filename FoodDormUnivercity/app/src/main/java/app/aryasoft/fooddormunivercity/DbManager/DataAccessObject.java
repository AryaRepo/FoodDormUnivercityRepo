package app.aryasoft.fooddormunivercity.DbManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import app.aryasoft.fooddormunivercity.Models.Student;

public class DataAccessObject
{
    private SQLiteDatabase db;
    private DataAccessLayer DAL;

    public DataAccessObject(DataAccessLayer DAL)
    {
        this.DAL = DAL;

    }

    public ArrayList<Student> getDeliveredReservation()
    {

        ArrayList<Student> deliveredReservationList = new ArrayList<>();
        try
        {
            this.db = DAL.OpenDataBase();
            Cursor cur = db.rawQuery("SELECT * FROM Student ;", null);
            if (cur.getCount() > 0)
            {
                if (cur.moveToFirst())
                {
                    do
                    {
                        Student deliveredStudents = new Student();
                        deliveredStudents.StudentId = cur.getInt(1);
                        deliveredStudents.StudentName = cur.getString(2);
                        deliveredStudents.StudentFamily = cur.getString(3);
                        deliveredStudents.StudentCode = cur.getString(4);
                        deliveredStudents.StudentFoodName = cur.getString(5);
                        deliveredStudents.ReserveState = cur.getInt(6);
                        //-----------------------------
                        deliveredReservationList.add(deliveredStudents);
                    } while (cur.moveToNext());
                }
            }
            cur.close();
        } catch (Exception exp)
        {
            Log.i("deliveredReservation: ", exp.getMessage());
        }
        closeDb();
        return deliveredReservationList;
    }

    public boolean addDeliveredReservation(Student reservationModel)
    {
        try
        {
            this.db = DAL.OpenDataBase();
            db.execSQL("INSERT INTO Student " + "(StudentId,StudentName,StudentFamily,StudentCode,StudentFoodName,ReserveState)" +
            " VALUES " + "('" + reservationModel.StudentId + "','" + reservationModel.StudentName+ "','"+ reservationModel.StudentFamily+ "','"+ reservationModel.StudentCode+ "','"+reservationModel.StudentFoodName+ "','"+reservationModel.ReserveState + "' )");
            closeDb();
            return true;

        } catch (Exception exp)
        {
            Log.i("exp : ", exp.getMessage());
            return false;
        }

    }

    public boolean removeDeliveredReservation(int studentId)
    {
        this.db = DAL.OpenDataBase();
        try
        {
            db.execSQL("DELETE FROM Student  WHERE StudentId=" + studentId);
            return true;
        } catch (Exception exp)
        {
            Log.i("exp : ", exp.getMessage());
            return false;
        }

    }

    public void closeDb()
    {
        if (db != null)
        {
            db.close();
        }
    }

}
