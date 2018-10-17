package app.aryasoft.fooddormunivercity.DbManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import app.aryasoft.fooddormunivercity.DbManager.DbModels.DeliveredReservationModel;

public class DataAccessObject
{
    private SQLiteDatabase db;
    private DataAccessLayer DAL;

    public DataAccessObject(DataAccessLayer DAL)
    {
        this.DAL = DAL;

    }

    public ArrayList<DeliveredReservationModel> getDeliveredReservation()
    {
        this.db = DAL.OpenDataBase();
        ArrayList<DeliveredReservationModel> deliveredReservationList = new ArrayList<>();
        try
        {
            Cursor cur = db.rawQuery("SELECT * FROM deliveredReservation ;", null);
            if (cur.getCount() > 0)
            {
                if (cur.moveToFirst())
                {
                    do
                    {
                        DeliveredReservationModel deliveredReservationModel = new DeliveredReservationModel();
                        deliveredReservationModel.RowId = cur.getInt(0);
                        deliveredReservationModel.StudentID = cur.getInt(1);
                        deliveredReservationModel.StudentCode = cur.getInt(2);
                        //-----------------------------
                        deliveredReservationList.add(deliveredReservationModel);
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

    public boolean addDeliveredReservation(DeliveredReservationModel reservationModel)
    {
        this.db = DAL.OpenDataBase();
        try
        {
            db.execSQL("INSERT INTO DeliveredReservation " + "(StudentID,StudentCode)" + " VALUES " + "('" + reservationModel.StudentID + "','" + reservationModel.StudentCode + "' )");
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
