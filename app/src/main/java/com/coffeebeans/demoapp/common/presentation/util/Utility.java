package com.coffeebeans.demoapp.common.presentation.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.WindowManager;

import com.coffeebeans.demoapp.R;

public class Utility {

    private static AlertDialog alertDialog;

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

    public static void showAlertDialogOKCancelAction(final Context context, String title, String message, String positiveText, String negativeText, DialogInterface.OnClickListener positiveClickListener) {
        if (alertDialog != null && alertDialog.isShowing()) return;

        alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveText, positiveClickListener)
                .setNegativeButton(negativeText, (dialog, whichButton) -> dialog.dismiss())
                .setCancelable(true)
                .create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
        alertDialog.show();
    }
}
