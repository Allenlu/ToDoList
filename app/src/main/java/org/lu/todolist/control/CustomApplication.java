package org.lu.todolist.control;

import android.app.Application;
import android.util.DisplayMetrics;

import org.lu.todolist.model.Constant;


public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DisplayMetrics dm=getResources().getDisplayMetrics();
        Constant.SCREEN_WIDTH = dm.widthPixels;
        Constant.SCREEN_HEIGHT =dm.heightPixels;
        Constant.DENSITY_DPI = getResources().getDisplayMetrics().densityDpi;
    }
}
