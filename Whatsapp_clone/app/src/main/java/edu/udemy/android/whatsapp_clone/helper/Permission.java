package edu.udemy.android.whatsapp_clone.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permission {

    /**
     * A static method to validate the permissions of the app.
     * If there are some permission that not allowed yet, this
     * method will ask to user to it.
     *
     * @param activity activity will need the permission.
     * @param permissions needed permissions to activity work well.
     * @param requestCode a integer to management who and what request.
     *
     * @return a boolean indicating that all permission were allowed.
     */
    public static Boolean validatePermission( Activity activity, String[] permissions, int requestCode ) {
        Boolean is_allowed = true;

        /*
        * This validation is necessary, because from the
        *   Marshmallows version od Android we must ask
        *   to users if they allow us to use some resources
        *   of their cellphones.
        *
        *   For this, we must to do this validation.
        * */
        if( Build.VERSION.SDK_INT >= 23 ) {
            List<String> listPermissions = new ArrayList<>();

            for( String permission : permissions ) {
                Boolean allowedPermissions = ContextCompat.checkSelfPermission( activity, permission ) ==
                        PackageManager.PERMISSION_GRANTED;

                if( !allowedPermissions ) {
                    listPermissions.add( permission );
                }
            }

            if( listPermissions.isEmpty() ) {
                is_allowed = true;
            }

            String[] arrPermission = new String[ listPermissions.size() ];
            listPermissions.toArray( arrPermission );

            ActivityCompat.requestPermissions( activity, arrPermission, requestCode );
        }

        return is_allowed;
    }
}
