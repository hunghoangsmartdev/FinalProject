package finalproject.smartdev.vn.finalproject.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import finalproject.smartdev.vn.finalproject.R;
import finalproject.smartdev.vn.finalproject.constants.Constants;
import finalproject.smartdev.vn.finalproject.infrastructure.Utils;


public class MainActivity extends ActionBarActivity implements DrawerLayout.DrawerListener
, AdapterView.OnItemClickListener
{

    private static final String TAG = "HomeActivity";
    private boolean isExitApplication = false;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] navMenuTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createActionBar();
        Utils.navigationLoginFragment(getSupportFragmentManager(),null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG,"7");
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {



        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager()
                .findFragmentByTag(HomeFragment.TAG);
        if (homeFragment != null) {
            homeFragment.onBackPress();
            isExitApplication = false;
            return;
        }



        ProfileFragment profileFragment = (ProfileFragment) getSupportFragmentManager()
                .findFragmentByTag(ProfileFragment.TAG);
        if (profileFragment != null) {
            profileFragment.onBackPress();
            isExitApplication = false;
            return;
        }



        FavoriteFragment favoriteFragment = (FavoriteFragment) getSupportFragmentManager()
                .findFragmentByTag(FavoriteFragment.TAG);
        if (favoriteFragment != null) {
            favoriteFragment.onBackPress();
            isExitApplication = false;
            return;
        }



        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager()
                .findFragmentByTag(LoginFragment.TAG);
        if (loginFragment != null) {
            loginFragment.onBackPress();
            this.finish();
            return;
        }


    }

    private void createActionBar()
    {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        navMenuTitles = new String []{Constants.MENU_LEFT_PROFILE,Constants.MENU_LEFT_FAVORITE, Constants.MENU_LEFT_EXIT};
        ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,navMenuTitles );
        mDrawerList.setAdapter(codeLearnArrayAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.drawable.ic_home,R.string.ok,R.string.error);
        mDrawerLayout.setDrawerListener(this);
        mDrawerList.setOnItemClickListener(this);

    }


    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        Log.i(TAG,"1");
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        invalidateOptionsMenu();
        Log.i(TAG,"2");
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        invalidateOptionsMenu();
        Log.i(TAG,"3");
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        Log.i(TAG,"7");
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        Log.i(TAG,"4");
        return super.onPrepareOptionsMenu(menu);

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        Log.i(TAG,"5");
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.i(TAG,"6");
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG,"4");
        if(navMenuTitles[position].equalsIgnoreCase(Constants.MENU_LEFT_PROFILE))
        {
            Utils.navigationProfileFragment(getSupportFragmentManager(),null);

        }else if(navMenuTitles[position].equalsIgnoreCase(Constants.MENU_LEFT_FAVORITE))
        {
            Utils.navigationFavoriteFragment(getSupportFragmentManager(),null);

        }else if(navMenuTitles[position].equalsIgnoreCase(Constants.MENU_LEFT_EXIT))
        {
              finish();
        }
    }

}
