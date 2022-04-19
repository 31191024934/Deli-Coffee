package project.edu.example.delicoffee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //database version
    private static final int DATABASE_VERSION = 1;

    // database name
    private static final String DATABASE_NAME = "delicoffee";

    // table name
    private static final String TBL_USER="tbl_user";

    // column name
    private static final String ID_USER = "id_user";
    private static final String USER_NAME = "user_name";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private static final String CREATE_TBL_USER =
            "CREATE TABLE" + TBL_USER
            + "("
                    + ID_USER + " INTERGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + USER_NAME + " VARCHAR, "
                    + PHONE + " VARCHAR, "
                    + EMAIL + " VARCHAR, "
                    + PASSWORD + " VARCHAR, "

                + ")";



    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
