package edu.udemy.android.sharedpreferencessample;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_PREFERENCES = "File Preferences";
    private static  final int ONLY_THIS_APP = 0;

    private Button btn_changeColor;
    private RadioGroup rdg_colors;
    private RadioButton btr_chosenColor;

    private RelativeLayout activity_main;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        rdg_colors = (RadioGroup) findViewById( R.id.rdg_colors );
        btn_changeColor = (Button) findViewById( R.id.btn_changeColor );
        activity_main = (RelativeLayout) findViewById( R.id.activity_main );

        btn_changeColor.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                int idChosenColor = rdg_colors.getCheckedRadioButtonId();

                if( idChosenColor > 0 ) {
                    btr_chosenColor = (RadioButton) findViewById( idChosenColor );

                    SharedPreferences sharedPreferences =
                            getSharedPreferences( "COLOR", ONLY_THIS_APP );

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    String chosenColor = btr_chosenColor.getText().toString();

                    editor.putString( "CHOSEN COLOR", chosenColor );
                    editor.commit();

                    setBackground( chosenColor );
                }
            }
        } );

        SharedPreferences sharedPreferences = getSharedPreferences( "COLOR", ONLY_THIS_APP );

        if( sharedPreferences.contains( "CHOSEN COLOR" ) ) {
            String chosenColor = sharedPreferences.getString( "CHOSEN COLOR", "Laranja" );

            setBackground( chosenColor );
        }
    }

    private void setBackground( String chosenColor ) {
        switch( chosenColor ) {
            case "Blue":
                activity_main.setBackgroundColor( Color.parseColor( "#0000b3" ) );
                break;
            case "Purple":
                activity_main.setBackgroundColor( Color.parseColor( "#800080" ) );
                break;
            case "Green":
                activity_main.setBackgroundColor( Color.parseColor( "#004d00" ) );
        }
    }
}
