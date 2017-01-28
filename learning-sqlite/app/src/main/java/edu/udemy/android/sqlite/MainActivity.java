package edu.udemy.android.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn_show;
    private TextView txv_show;

    private SQLiteDatabase database;
    private Cursor cursor;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        database = openOrCreateDatabase( "app", MODE_PRIVATE, null );

        // Create Table
        database.execSQL( "CREATE TABLE IF NOT EXISTS people( name VACHAR, age INT(3) ) " );

        //Insert Table
        database.execSQL( "INSERT INTO people ( name, age ) VALUES ( 'Thaiane', 24 )" );
        database.execSQL( "INSERT INTO people ( name, age ) VALUES ( 'Tomaz', 29 )" );
        database.execSQL( "INSERT INTO people ( name, age ) VALUES ( 'Thaiane e Tomaz', 7 )" );


        btn_show = (Button) findViewById( R.id.btn_show );
        txv_show = (TextView) findViewById( R.id.txv_show );

        btn_show.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                travelDatabase( database );
            }
        } );
    }

    @Override
    protected void onDestroy() {
        database.close();
        cursor.close();

        super.onDestroy();
    }

    private void travelDatabase( SQLiteDatabase database ) {
        cursor = database.rawQuery( "SELECT name, age FROM people", null );

        cursor.moveToFirst();

        String result = "";

        // Iterate over Rows
        //
        for( int i = 0; i < cursor.getCount(); i++ ) {

            // Iterate over Columns
            //
            for( int j = 0; j < cursor.getColumnNames().length; j++ ) {
                result += cursor.getString( j ) + " ";
            }

            cursor.moveToNext();

            result += "\n";
        }

        txv_show.setText( result );
    }
}
