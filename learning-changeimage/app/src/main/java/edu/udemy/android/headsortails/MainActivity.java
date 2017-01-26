package edu.udemy.android.headsortails;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public enum Option {
        HEADS( "Head" ),
        TAILS( "Tail" );

        private String name;

        Option( String name ) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    private Button btn_play;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btn_play = (Button) findViewById( R.id.btn_play );

        btn_play.setOnClickListener( new View.OnClickListener() {
            Random random = new Random();
            int randomNumber = random.nextInt( 2 );

            @Override
            public void onClick( View view ) {
                Intent startCoinActivity = new Intent( MainActivity.this, CoinActivity.class );

                switch( randomNumber ) {
                    case 0:
                        startCoinActivity.putExtra( "result", Option.HEADS.getName() );
                        break;
                    case 1:
                        startCoinActivity.putExtra( "result", Option.TAILS.getName() );
                }

                startActivity( startCoinActivity );
            }
        } );
    }
}
