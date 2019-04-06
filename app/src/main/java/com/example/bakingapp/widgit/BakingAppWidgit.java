package com.example.bakingapp.widgit;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.example.bakingapp.R;
import com.example.bakingapp.fragment.RecipeStepFragment;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidgit extends AppWidgetProvider {
    
    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId, String ingredients) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widgit);
        views.setTextViewText(R.id.appwidget_text, ingredients);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        SharedPreferences sharedPreferences = context.getSharedPreferences(RecipeStepFragment.SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(RecipeStepFragment.INGREDIENT_PREFERENCES, null);
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId,result);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

