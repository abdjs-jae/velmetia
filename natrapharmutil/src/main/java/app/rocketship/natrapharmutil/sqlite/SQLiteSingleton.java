package app.rocketship.natrapharmutil.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Candice on 15/02/2017.
 */

public class SQLiteSingleton {

    public enum DBMode{
        READ, WRITE
    }

    private static SQLiteSingleton instance;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase dbReadable;
    private SQLiteDatabase dbWritable;
    private static Context context;


    private SQLiteSingleton(Context context){
        this.context = context;
        sqLiteOpenHelper = getHelper();
    }

    public static synchronized SQLiteSingleton getInstance(Context context){
        if(instance == null){
            instance = new SQLiteSingleton(context);
        }

        return instance;
    }

    public SQLiteOpenHelper getHelper(){
        if(sqLiteOpenHelper == null){
            sqLiteOpenHelper = new SQLHelper(context);
        }

        return sqLiteOpenHelper;
    }

    public SQLiteDatabase getDatabase(DBMode mode){
        switch (mode){
            case READ:
                if(dbReadable == null)
                    dbReadable = getHelper().getReadableDatabase();
                return dbReadable;
            case WRITE:
                if(dbWritable == null)
                    dbWritable = getHelper().getWritableDatabase();
                return dbWritable;
        }


        return null;
    }

    public void close(){
        sqLiteOpenHelper.close();
        instance = null;
    }


}
