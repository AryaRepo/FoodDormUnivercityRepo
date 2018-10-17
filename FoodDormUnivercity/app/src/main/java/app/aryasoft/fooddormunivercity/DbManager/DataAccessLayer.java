package app.aryasoft.fooddormunivercity.DbManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DataAccessLayer extends SQLiteOpenHelper
{
    private static String dbpath;
    private static String dbname = "studentdb.db";
    private Context AppContext;
    private SQLiteDatabase db;

    public DataAccessLayer(Context AppContext)
    {
        super(AppContext, dbname, null, 1);
        this.AppContext = AppContext;
        dbpath = String.format("//data//data//%s//databases//", AppContext.getPackageName());
        PrepareDataBase();
    }

    private void PrepareDataBase()
    {
        boolean dbExist = CheckDataBase();
        if (!dbExist)
        {
           this.getReadableDatabase();
            CopyDataBase();
        }
    }

    private void CopyDataBase()
    {
        try
        {
            InputStream myInput = AppContext.getAssets().open(dbname);
            String outFileName = dbpath + dbname;
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0)
            {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception exp)
        {
            Log.i("CopyDataBase :", exp.getMessage());
        }
    }

    private boolean CheckDataBase()
    {
        SQLiteDatabase checkDB = null;
        try
        {
            String myPath = dbpath + dbname;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e)
        {
            Log.i("CheckDataBase :", e.getMessage());
        }

        if (checkDB != null)
        {
            checkDB.close();
        }
        return checkDB != null;
    }

    public SQLiteDatabase OpenDataBase( )
    {
        String myPath = dbpath + dbname;
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }

    @Override
    public synchronized SQLiteDatabase getReadableDatabase()
    {
        return super.getReadableDatabase();
    }
}