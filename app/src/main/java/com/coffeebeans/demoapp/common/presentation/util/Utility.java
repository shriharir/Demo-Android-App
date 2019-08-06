package com.coffeebeans.demoapp.common.presentation.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.WindowManager;

public class Utility {

    private static AlertDialog alertDialog;

    public static void showAlertDialogYesAndNo(final Activity activity) {
        new AlertDialog.Builder(activity)
                .setMessage("u want to exit")
                .setPositiveButton("Yes", (arg0, arg1) -> activity.finish())
                .setNegativeButton("No", (arg0, arg1) -> {
                })
                .show();
    }


    public static void showAlertDialogOK(final Context context, String message, String title) {
        if (alertDialog != null && alertDialog.isShowing()) return;

        alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", (dialog, arg1) -> dialog.dismiss())
                .setCancelable(true)
                .create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
        alertDialog.show();
    }

}
