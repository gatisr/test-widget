package com.example.testwidget;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RemoteViews;

public class Config extends Activity implements OnClickListener {
	private Config context;
	private int widgetId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configure);
		setResult(RESULT_CANCELED); // do nothing when user click back button
		context = this;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(this);		
	}
	@Override
	public void onClick(View v) {
		AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
		if (v.getId() == R.id.button1) {
			AutoCompleteTextView et = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(et.getText().toString()));
			PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_main);
			views.setOnClickPendingIntent(R.id.imageButton1, pending);
			widgetManager.updateAppWidget(widgetId, views);
			
			Intent resultVlue = new Intent();
			resultVlue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
			setResult(RESULT_OK, resultVlue);
			finish();
		}
		
	}

}
