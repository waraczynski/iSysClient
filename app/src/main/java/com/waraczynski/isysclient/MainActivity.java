package com.waraczynski.isysclient;

//import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

// e    xtends ActionBarActivity
public class MainActivity extends Activity  {
    iSysMenuData iSysMenu;
    ExpandableListAdapter modules_list_adapter;
    ExpandableListView modules_list_view;
    List<String> modules_list_groups;
    List<String> group_items;
    HashMap<String, List<String>> modules_list_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listview
        modules_list_view = (ExpandableListView) findViewById(R.id.modules_list_view);

        prepareListData();

        modules_list_adapter = new ExpandableListAdapter(this, modules_list_groups, modules_list_items);

        // setting list adapter
        modules_list_view.setAdapter(modules_list_adapter);
    }

    private void prepareListData() {
        modules_list_groups = new ArrayList<String>();
        modules_list_items = new HashMap<String, List<String>>();

        iSysMenu = new iSysMenuData();
        iSysMenu.getJSONFromUrl("http://isys.waraczynski.com/Android/integration.php");

        for (int i = 0;i<3;i++) {
            modules_list_groups.add("Grupa["+i+"]");

            group_items = new ArrayList<String>();
            for (int j = 0;j<5;j++) {
                group_items.add("Pozycja[" + i + "," + j + "]");
            }
            modules_list_items.put(modules_list_groups.get(i), group_items);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingslIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingslIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
