package com.example.walterlp.rpgclass.activity.activity.utils;

import android.content.Context;
import android.widget.EditText;

import com.example.walterlp.rpgclass.R;

import java.util.List;

public class UiUtils {

    public static  boolean validFields(List<EditText> edits, Context context){
        EditText focusInput = null;
        boolean isValid = true;

        for ( EditText e: edits) {
            if(e.getText().toString().length() == 0){
             focusInput = e;
             e.requestFocus();
             e.setError(context.getResources().getString(R.string.campo_obrigatorio));

                isValid = false;
            }
            if (focusInput != null) {
                focusInput.requestFocus();
            }

        }
        return isValid;

    }
}
