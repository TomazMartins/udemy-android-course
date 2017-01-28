package edu.udemy.android.tasklist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String DB_NAME = "Task List";
    private static final int EMPTY = 0;


    private Button btn_addtask;
    private EditText edt_taskname;
    private ListView lsv_tasks;

    private SQLiteDatabase database;

    private ArrayAdapter<String> taskAdapter;
    private ArrayList<String> tasks;
    private ArrayList<Integer> ids;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        createDatabase();
        setComponents();

        btn_addtask.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                String digitedText = edt_taskname.getText().toString();

                insertData( digitedText );
            }
        } );

        lsv_tasks.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
                deleteData( ids.get( position ) );
            }
        } );

        showData();
    }

    @Override
    protected void onDestroy() {
        database.close();

        super.onDestroy();
    }

    private void setComponents() {
        edt_taskname = (EditText) findViewById( R.id.edt_taskname );
        btn_addtask = (Button) findViewById( R.id.btn_addtask );
        lsv_tasks = (ListView) findViewById( R.id.lsv_tasks );
    }

    private void createDatabase() {
        database = openOrCreateDatabase( DB_NAME, MODE_PRIVATE, null );

        database.execSQL( "CREATE TABLE IF NOT EXISTS tasks(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "task VARCHAR" +
                ")" );
    }

    private void insertData( String text ) {
        if( !text.isEmpty() ) {
            database.execSQL( "INSERT INTO tasks( task ) VALUES( '" + text + "' )" );

            Toast.makeText( MainActivity.this, R.string.add_task_success, Toast.LENGTH_SHORT )
                    .show();

            edt_taskname.setText( "" );

            showData();
        } else {
            Toast.makeText( MainActivity.this, R.string.msg_without_name, Toast.LENGTH_SHORT )
                    .show();
        }
    }

    private void showData() {
        Cursor cursor = database.rawQuery( "SELECT * FROM tasks ORDER BY id DESC", null );

        int indexColumnId = cursor.getColumnIndex( "id" );
        int indexColumnTask = cursor.getColumnIndex( "task" );

        ids = new ArrayList<Integer>();
        tasks = new ArrayList<String>();
        setListView( tasks );

        cursor.moveToFirst();

        String task = "";
        Integer id = 0;

        if( cursor.getCount() != EMPTY ) {
            do {
                task = cursor.getString( indexColumnTask );
                id = Integer.parseInt( cursor.getString( indexColumnId ) );

                tasks.add( task );
                ids.add( id );
            } while( cursor.moveToNext() );
        }

        cursor.close();
    }

    private void deleteData( Integer id ) {
        database.execSQL( "DELETE FROM tasks WHERE id=" + id );

        showData();
    }

    private void setListView( ArrayList<String> list ) {
        taskAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                list
        );

        lsv_tasks.setAdapter( taskAdapter );
    }
}
