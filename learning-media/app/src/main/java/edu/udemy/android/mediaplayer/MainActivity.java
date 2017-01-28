package edu.udemy.android.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_play;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btn_play = (Button) findViewById( R.id.btn_play );
        mediaPlayer = MediaPlayer.create( MainActivity.this, R.raw.musica );

        btn_play.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if( mediaPlayer.isPlaying() ) {
                    pauseMusic();
                } else {
                    playMusic();
                }
            }
        } );
    }

    @Override
    protected void onDestroy() {
        // Used to free resources.
        //
        //   When we use resource of media, we must to do it.
        //   Because media take a lot of resource of cellphone;

        if( mediaPlayer != null && mediaPlayer.isPlaying() ) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        super.onDestroy();
    }

    private void playMusic() {
        if( mediaPlayer != null ) {
            mediaPlayer.start();

            btn_play.setText( "Pause" );
        }
    }

    private void pauseMusic() {
        if( mediaPlayer != null ) {
            mediaPlayer.pause();

            btn_play.setText( "Play" );
        }
    }
}
