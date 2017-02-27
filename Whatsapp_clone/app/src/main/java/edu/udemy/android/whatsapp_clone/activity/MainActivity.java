package edu.udemy.android.whatsapp_clone.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import edu.udemy.android.whatsapp_clone.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        createComponents();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_main, menu );

        return true;
    }

    /**
     * Create references to components of user interface.
     */
    private void createComponents() {
        toolbar = (Toolbar) findViewById( R.id.toolbar );

        setToolbar();
    }

    /**
     * Configure the Toolbar
     */
    private void setToolbar() {
        toolbar.setTitle( "Whatsapp" );
        setSupportActionBar( toolbar );
    }
}
