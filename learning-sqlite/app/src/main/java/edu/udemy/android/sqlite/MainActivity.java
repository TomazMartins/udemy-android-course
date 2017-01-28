package edu.udemy.android.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int EMPTY = 0;

    private Button btn_show;
    private Button btn_insert;
    private Button btn_delete;
    private TextView txv_text;

    private SQLiteDatabase database;
    private Cursor cursor;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        database = openOrCreateDatabase( "app", MODE_PRIVATE, null );

        // Create Table
        database.execSQL( "CREATE TABLE IF NOT EXISTS people( name VACHAR, age INT(3) ) " );


        btn_show = (Button) findViewById( R.id.btn_show );

        btn_show.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                travelDatabase();
            }
        } );

        btn_insert = (Button) findViewById( R.id.btn_insert );

        btn_insert.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                insertData();
            }
        } );

        btn_delete = (Button) findViewById( R.id.btn_delete );

        btn_delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                deleteData();
            }
        } );

        txv_text = (TextView) findViewById( R.id.txv_text );
    }

    @Override
    protected void onDestroy() {
        database.close();
        cursor.close();

        super.onDestroy();
    }

    private void deleteData() {
        if( database.isOpen() ) {
            database.execSQL( "DELETE FROM people" );
        } else {
            txv_text.setText( R.string.db_not_opened );
        }
    }

    private void insertData() {
        if( database.isOpen() ) {
            database.execSQL( "INSERT INTO people ( name, age ) VALUES ( 'Thaiane', 24 )" );
            database.execSQL( "INSERT INTO people ( name, age ) VALUES ( 'Tomaz', 29 )" );
            database.execSQL( "INSERT INTO people ( name, age ) VALUES ( 'Thaiane e Tomaz', 7 )" );
        } else {
            txv_text.setText( R.string.db_not_opened );
        }
    }

    private void travelDatabase() {
        if( database.isOpen() ) {
            cursor = database.rawQuery( "SELECT name, age FROM people", null );

            int indexColumnName = cursor.getColumnIndex( "name" );
            int indexColumnAge = cursor.getColumnIndex( "age" );

            cursor.moveToFirst();

            String name;
            String age;
            String result = "";

            if( cursor.getCount() != EMPTY ) {
                do {
                    name = cursor.getString( indexColumnName );
                    age = cursor.getString( indexColumnAge );

                    result += name;
                    result += " ";
                    result += age;
                    result += "\n";
                } while( cursor.moveToNext() );

                txv_text.setText( result );
            } else {
                txv_text.setText( R.string.db_empty );
            }
        } else {
            txv_text.setText( R.string.db_not_opened );
        }
    }



//    // Another form to travel over Database
//    //
//    private void travelDatabase( SQLiteDatabase database ) {
//        cursor = database.rawQuery( "SELECT name, age FROM people", null );
//
//        cursor.moveToFirst();
//
//        String result = "";
//
//        // Iterate over Rows
//        //
//        for( int i = 0; i < cursor.getCount(); i++ ) {
//
//            // Iterate over Columns
//            //
//            for( int j = 0; j < cursor.getColumnNames().length; j++ ) {
//                result += cursor.getString( j ) + " ";
//            }
//
//            cursor.moveToNext();
//
//            result += "\n";
//        }
//
//        txv_show.setText( result );
//    }
}
