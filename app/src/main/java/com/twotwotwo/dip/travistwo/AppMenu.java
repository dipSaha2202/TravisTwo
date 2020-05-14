package com.twotwotwo.dip.travistwo;

import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AppMenu extends ListActivity {
    SharedPreferences preferences;
    private String[] appNameArray = {"BasicGraphics", "SurfaceGraphicsBasic", "SoundView", "Sliding",
            "Flipper", "SharedAndInternal"};
    Intent preferencesIntent;
    Intent aboutIntent;
    ArrayAdapter<String> appListAdapter;
    TextView textListView;
    private int textSize;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String selectedClassName = appNameArray[position];
        Class selectedClass;

        try {
            selectedClass = Class.forName("com.twotwotwo.dip.travistwo." + selectedClassName);
            Intent selectedAppIntent = new Intent(AppMenu.this, selectedClass);
            startActivity(selectedAppIntent);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(AppMenu.this, "Class not found", Toast.LENGTH_SHORT).show();
        } catch (ActivityNotFoundException e){
            Toast.makeText(AppMenu.this, "Activity not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.app_list_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()){
            case R.id.preferences_appListMenu:
                preferencesIntent = new Intent(AppMenu.this, PreferencesApp.class);
                startActivity(preferencesIntent);
                break;
            case R.id.exitApp_appListMenu:
                finish();
                break;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferences = PreferenceManager.getDefaultSharedPreferences(AppMenu.this);
        String appTitle = preferences.getString(getString(R.string.app_title_preferences_key),
                                                                         "DipApp");
        getActionBar().setTitle(appTitle);

        String textSizeString = preferences.getString(
                  getString(R.string.preferences_app_text_size_key), "18");
        textSize = Integer.parseInt(textSizeString);

        appListAdapter = new ArrayAdapter<String>(AppMenu.this,
                R.layout.app_list_row, appNameArray){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if(convertView == null){
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.app_list_row, parent, false);
                    textListView =  convertView.findViewById(R.id.textLine);
                    textListView.setTextSize((float) textSize);
                }
                textListView.setText(appNameArray[position]);
                return convertView;
            }
        };
        setListAdapter(appListAdapter);
    }
}
