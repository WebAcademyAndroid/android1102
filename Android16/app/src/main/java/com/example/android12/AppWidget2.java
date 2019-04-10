package com.example.android12;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AppWidget2 extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        DataBaseHelper helper = new DataBaseHelper(context);
        Widget widget = helper.getWidget(appWidgetId);
        if (widget != null) {
            Student student = helper.getStudent(widget.idStudent);
            if (student != null) {
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget2);
                views.setTextViewText(R.id.textViewFirstName, student.firstName);
                views.setTextViewText(R.id.textViewLastName, student.lastName);
                views.setTextViewText(R.id.textViewAge, String.valueOf(student.age));

                Intent intent = new Intent(context, Activity2.class);
                intent.putExtra("id", student.id);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, IntManager.nextInt(context), intent,0);
                views.setOnClickPendingIntent(R.id.widget, pendingIntent);

                appWidgetManager.updateAppWidget(appWidgetId, views);
            }
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        DataBaseHelper helper = new DataBaseHelper(context);
        for (int appWidgetId : appWidgetIds) {
            helper.deleteWidget(appWidgetId);
        }
    }
}

