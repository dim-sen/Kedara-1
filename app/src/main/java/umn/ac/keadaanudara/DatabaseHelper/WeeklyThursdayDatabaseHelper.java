package umn.ac.keadaanudara.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import umn.ac.keadaanudara.Model.WeeklyThursdayActivityModel;

public class WeeklyThursdayDatabaseHelper extends SQLiteOpenHelper {
    public static final String THURSDAY_ACTIVITY_TABLE = "THURSDAY_ACTIVITY_TABLE";
    public static final String COLUMN_ACTIVITY_NAME = "ACTIVITY_NAME";
    public static final String COLUMN_ACTIVITY_LOCATION = "ACTIVITY_LOCATION";
    public static final String COLUMN_ACTIVITY_TIME = "ACTIVITY_TIME";
    public static final String COLUMN_ACTIVITY_REMINDER = "ACTIVITY_REMINDER";

    public WeeklyThursdayDatabaseHelper(@Nullable Context context){
        super(context, "thursdayActivity.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + THURSDAY_ACTIVITY_TABLE + " (" + COLUMN_ACTIVITY_NAME + " TEXT, " + COLUMN_ACTIVITY_LOCATION + " TEXT, " + COLUMN_ACTIVITY_TIME + " TEXT, " + COLUMN_ACTIVITY_REMINDER + " INT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(WeeklyThursdayActivityModel thursdayModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ACTIVITY_NAME, thursdayModel.getActivity());
        cv.put(COLUMN_ACTIVITY_LOCATION, thursdayModel.getLocation());
        cv.put(COLUMN_ACTIVITY_TIME, thursdayModel.getTime());
        cv.put(COLUMN_ACTIVITY_REMINDER, thursdayModel.getReminders());

        long insert = db.insert(THURSDAY_ACTIVITY_TABLE, null, cv);

        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }


    public Cursor getEveryone(){
        List<String> activityNameList = new ArrayList<>();
        List<String> activityLocationList = new ArrayList<>();
        List<String> activityTimeList = new ArrayList<>();

        String queryString = "SELECT * FROM " + THURSDAY_ACTIVITY_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);


        return cursor;
    }
}
