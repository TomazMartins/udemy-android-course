/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        ParseObject punctuation = new ParseObject( "Punctuation" );
        punctuation.put( "name", "Mário" );
        punctuation.put( "punctuation", 800 );
        punctuation.saveInBackground( new SaveCallback() {
            @Override
            public void done( ParseException pe ) {
                if( pe == null ) {
                    Log.i( "SAVE POINTS", "Save data with success" );
                } else {
                    Log.i( "SAVE POINTS", "Fail while save data" );
                }
            }
        } );
    }
}
