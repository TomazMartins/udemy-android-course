package edu.udemy.android.myannotation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private static final String FILE = "annotation.txt";

    private Button btn_save;
    private EditText edt_text;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btn_save = (Button) findViewById( R.id.btn_save );
        edt_text = (EditText) findViewById( R.id.edt_text );

        btn_save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                String digitedText = edt_text.getText().toString();
                recordFile( digitedText );
            }
        } );

        if( readFile() != null ) {
            edt_text.setText( readFile() );
        }
    }

    private void recordFile( String text ) {
        try {
            OutputStreamWriter writer =
                    new OutputStreamWriter( openFileOutput( FILE, Context.MODE_PRIVATE ) );

            writer.write( text );
            writer.close();

            Toast.makeText( MainActivity.this, "File recorded", Toast.LENGTH_SHORT ).show();
        } catch( IOException ioe ) {
            Log.v( "MainActivity", ioe.toString() );
        }
    }

    private String readFile() {
        String result = "";

        try {
            InputStream file = openFileInput( FILE );

            if( file != null ) {
                InputStreamReader reader = new InputStreamReader( file );

                BufferedReader buffer = new BufferedReader( reader );

                String line = "";

                while( (line = buffer.readLine()) != null ) {
                    result += line;

                }

                file.close();
            }

        } catch( IOException ioe ) {
            Log.v( "MainActivity", ioe.toString() );
        }

        return result;
    }
}
