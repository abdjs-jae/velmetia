package app.rocketship.natrapharmutil.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Candice on 15/02/2017.
 */

public class SQLHelper extends SQLiteOpenHelper {

    public enum ClickColumns{
       PAGE_KEY("page_key"),
        TIMESTAMP("timestamp");

        private String columnName;

        ClickColumns(String columnName){
            this.columnName = columnName;
        }

        public String getColumnName(){
            return columnName;
        }

    }

    public enum Tables{
        CLICKS("clicks");

        private String tableName;

        Tables(String tableName){
            this.tableName = tableName;
        }

        public String getTableName(){

            return tableName;
        }
    }

    /*
    private static final String SQL_SELECT_CURRENTUSER =
            "SELECT FROM " +
    */
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Tables.CLICKS.getTableName() + " (" +
                    BaseColumns._ID + " INTEGER PRIMARY KEY," +
                    ClickColumns.PAGE_KEY.getColumnName() + " TEXT," +
                    ClickColumns.TIMESTAMP.getColumnName() + " DATETIME DEFAULT CURRENT_TIMESTAMP" +
            ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Tables.CLICKS.getTableName();

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Pedia.db";

    public SQLHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);

    }

    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }
}
