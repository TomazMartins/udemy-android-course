package edu.udemy.android.seekbarsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar skb_scale;
    private TextView txv_show;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        skb_scale = (SeekBar) findViewById( R.id.skb_scale );
        txv_show = (TextView) findViewById( R.id.txv_show );

        skb_scale.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged( SeekBar seekBar, int progress, boolean fromUser ) {
                txv_show.setText( "Progress: " + progress + " / " + seekBar.getMax() );
            }

            @Override
            public void onStartTrackingTouch( SeekBar seekBar ) {
                Toast.makeText( MainActivity.this, "SeekBar Pressed", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onStopTrackingTouch( SeekBar seekBar ) {
                Toast.makeText( MainActivity.this, "SeekBar Despressed", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
