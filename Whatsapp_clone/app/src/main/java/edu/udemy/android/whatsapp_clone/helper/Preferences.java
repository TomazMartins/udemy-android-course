package edu.udemy.android.whatsapp_clone.helper;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Preferences {
    public static final String KEY_USERNAME = "USERNAME";
    public  static final String KEY_CELLPHONE = "CELLPHONE";
    public  static final String KEY_TOKEN = "TOKEN";

    private static final String FILE_PREFERENCES = "whatsapp-clone-preferences";
    private static final int MODE_PRIVATE = 0;


    private Context mContext;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    public Preferences( Context context ) {
        mContext = context;

        preferences = mContext.getSharedPreferences( FILE_PREFERENCES, MODE_PRIVATE );
        editor = preferences.edit();
    }

    /**
     * Save the generated token in cellphone.
     *
     * @param userName  the name of the user.
     * @param cellphone the number of cellphone of user.
     * @param token     the generated token to save.
     */
    public void saveUserData( String userName, String cellphone, String token ) {
        editor.putString( KEY_USERNAME, userName );
        editor.putString( KEY_CELLPHONE, cellphone );
        editor.putString( KEY_TOKEN, token );

        editor.commit();
    }

    /**
     * Return the data of user saved in cellphone for validation.
     *
     * @return the uer data into a Hashmap.
     */
    public HashMap<String, String> getUserData() {
        HashMap<String, String> userData = new HashMap<>();

        userData.put( KEY_USERNAME, preferences.getString( KEY_USERNAME, null ) );
        userData.put( KEY_CELLPHONE, preferences.getString( KEY_CELLPHONE, null ) );
        userData.put( KEY_TOKEN, preferences.getString( KEY_TOKEN, null ) );

        return userData;
    }
}
