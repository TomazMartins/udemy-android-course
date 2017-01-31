package edu.udemy.android.sharedpreferencessample;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_PREFERENCES = "PreferenceFile";
    private static final int ONLY_THIS_APP = 0;

    private Button btn_save;
    private TextView txv_savedData;
    private EditText edt_name;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btn_save = (Button) findViewById( R.id.btn_save );
        txv_savedData = (TextView) findViewById( R.id.txv_savedData );
        edt_name = (EditText) findViewById( R.id.edt_name );

        btn_save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                SharedPreferences sharedPreference =
                        getSharedPreferences( FILE_PREFERENCES, ONLY_THIS_APP );

                SharedPreferences.Editor editor = sharedPreference.edit();

                if( !edt_name.getText().toString().equals( "" ) ) {
                    editor.putString( "NAME", edt_name.getText().toString() );
                    editor.commit();

                    txv_savedData.setText( edt_name.getText().toString() );
                } else {
                    Toast.makeText( MainActivity.this, "Please, insert a name", Toast.LENGTH_SHORT )
                            .show();
                }
            }
        } );

        SharedPreferences sharedPreference =
                getSharedPreferences( FILE_PREFERENCES, ONLY_THIS_APP );

        if( sharedPreference.contains( "NAME" ) ) {
            String userName = sharedPreference.getString( "NAME", "User don't defined" );
            txv_savedData.setText( userName );
        } else {
            txv_savedData.setText( "User don't defined" );
        }
    }
}
