package com.itdose.multitheme.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

public class ColorUtils {


    @ColorInt
    public static int getThemeColor(@NonNull Context context, @AttrRes int attrResId) {
        TypedArray a = context.obtainStyledAttributes(null, new int[]{attrResId});
        try {
            return a.getColor(0, Color.MAGENTA);
        } finally {
            a.recycle();
        }
    }
}
