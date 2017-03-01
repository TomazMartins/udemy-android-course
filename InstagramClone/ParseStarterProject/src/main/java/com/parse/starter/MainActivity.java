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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        updatePoints( "Tom", 200 );
    }


    /**
     * Update the punctuation of someone with Parse class.
     *
     * @param name name of someone who do the punctuation.
     * @param points the new punctuation.
     */
    private void updatePoints( final String name, final int points ) {

        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery( "Punctuation" );
        parseQuery.findInBackground( new FindCallback<ParseObject>() {
            @Override
            public void done( List<ParseObject> objects, ParseException e ) {
                if( e == null ) {
                    for( ParseObject object : objects ) {
                        if( object.getString( "name" ).equals( name ) ) {
                            object.put( "punctuation", points );
                            object.saveInBackground();
                        }
                    }

                    Log.i( "UPDATE POINTS", "Update data with success" );
                } else {
                    Log.i( "UPDATE POINTS", "Fail while update data" );
                }
            }
        } );
    }

    /**
     * Save the punctuation of someone with Parse class.
     *
     * @param name   name of someone who do the punctuation.
     * @param points total points of someone.
     */
    private void savePoints( String name, int points ) {
        ParseObject punctuation = new ParseObject( "Punctuation" );
        punctuation.put( "name", name );
        punctuation.put( "punctuation", points );

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
