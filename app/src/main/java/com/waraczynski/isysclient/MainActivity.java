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
    ExpandableListAdapter modules_list_adapter;
    ExpandableListView modules_list_view;
    List<String> modules_list_groups;
    HashMap<String, List<String>> modules_list_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listview
        modules_list_view = (ExpandableListView) findViewById(R.id.modules_list_view);

        // preparing list data
        prepareListData();

        modules_list_adapter = new ExpandableListAdapter(this, modules_list_groups, modules_list_items);

        // setting list adapter
        modules_list_view.setAdapter(modules_list_adapter);
    }

    private void prepareListData() {
        modules_list_groups = new ArrayList<String>();
        modules_list_items = new HashMap<String, List<String>>();

        // Adding child data
        modules_list_groups.add("Top 250");
        modules_list_groups.add("Now Showing");
        modules_list_groups.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        modules_list_items.put(modules_list_groups.get(0), top250);
        modules_list_items.put(modules_list_groups.get(1), nowShowing);
        modules_list_items.put(modules_list_groups.get(2), comingSoon);
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
