package edu.udemy.android.soundofanimals;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer media;

    private ImageView img_dog;
    private ImageView img_cat;
    private ImageView img_cow;
    private ImageView img_sheep;
    private ImageView img_lion;
    private ImageView img_monkey;

    @Override
    public void onClick( View view ) {
        switch( view.getId() ) {
            case R.id.img_dog:
                media = MediaPlayer.create( MainActivity.this, R.raw.dog );
                playSound();
                break;
            case R.id.img_cat:
                media = MediaPlayer.create( MainActivity.this, R.raw.cat );
                playSound();
                break;
            case R.id.img_cow:
                media = MediaPlayer.create( MainActivity.this, R.raw.cow );
                playSound();
                break;
            case R.id.img_sheep:
                media = MediaPlayer.create( MainActivity.this, R.raw.sheep );
                playSound();
                break;
            case R.id.img_lion:
                media = MediaPlayer.create( MainActivity.this, R.raw.lion );
                playSound();
                break;
            case R.id.img_monkey:
                media = MediaPlayer.create( MainActivity.this, R.raw.monkey );
                playSound();
        }
    }

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        img_dog = (ImageView) findViewById( R.id.img_dog );
        img_cat = (ImageView) findViewById( R.id.img_cat );
        img_cow = (ImageView) findViewById( R.id.img_cow );
        img_sheep = (ImageView) findViewById( R.id.img_sheep );
        img_lion = (ImageView) findViewById( R.id.img_lion );
        img_monkey = (ImageView) findViewById( R.id.img_monkey );

        img_dog.setOnClickListener( this );
        img_cat.setOnClickListener( this );
        img_cow.setOnClickListener( this );
        img_sheep.setOnClickListener( this );
        img_lion.setOnClickListener( this );
        img_monkey.setOnClickListener( this );
    }

    @Override
    protected void onDestroy() {
        if( media != null ) {
            media.release();
            media = null;
        }

        super.onDestroy();
    }

    private void playSound() {
        if( media != null ) {
            media.start();
        }
    }
}
