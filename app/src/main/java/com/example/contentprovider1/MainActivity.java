package com.example.contentprovider1;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void AccessContact(){
        List<String> permissionNeed= new ArrayList<String>();
        List<String> permissionExist= new ArrayList<String>();
        if (!addpermission(permissionExist, Manifest.permission.READ_CONTACTS))
            permissionNeed.add(("Read Contacts"));
        if (permissionNeed.size()>0){
            String msg = "Anda perlu memberikan akses "+permissionNeed.get(0);
            for (int i=1 ; i<permissionNeed.size();i++)
                msg = msg + ", "+permissionNeed.get(i);
            showMessage(msg, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //requestPermissions(permissionExist.toArray(new String[permissionExist.size()]), ActivityResultContracts.RequestMultiplePermissions);
                }
            });
        }

    }

    private void showMessage(String msg, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(msg)
                .setPositiveButton("OK",okListener)
                .setNegativeButton("Cancel",null)
                .create()
                .show();
    }

    private boolean addpermission(List<String> permissionExist, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permission)!= PackageManager.PERMISSION_GRANTED){
                permissionExist.add(permission);

                if(!shouldShowRequestPermissionRationale(permission))
                    return false;
            }
        }
        return true;
    }
}