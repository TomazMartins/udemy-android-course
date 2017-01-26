package edu.udemy.android.howmuchyoulike;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private SeekBar skb_scale;
    private ImageView img_result;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        skb_scale = (SeekBar) findViewById( R.id.skb_scale );
        img_result = (ImageView) findViewById( R.id.img_result );

        skb_scale.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged( SeekBar seekBar, int progress, boolean fromUser ) {

                switch( progress ) {
                    case 1:
                        img_result.setImageResource( R.drawable.emoticon_horror );
                        break;
                    case 2:
                        img_result.setImageResource( R.drawable.emoticon_oh );
                        break;
                    case 3:
                        img_result.setImageResource( R.drawable.emoticon_happy );
                        break;
                    case 4:
                        img_result.setImageResource( R.drawable.emoticon_smile );
                }
            }

            @Override
            public void onStartTrackingTouch( SeekBar seekBar ) {

            }

            @Override
            public void onStopTrackingTouch( SeekBar seekBar ) {

            }
        } );
    }
}
