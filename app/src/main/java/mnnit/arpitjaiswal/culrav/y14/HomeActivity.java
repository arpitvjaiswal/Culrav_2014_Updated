package mnnit.arpitjaiswal.culrav.y14;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // select first menu item on startup
        if (savedInstanceState == null) {
            navigationView.getMenu().performIdentifierAction(R.id.nav_home, 0);
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handle navigation view item clicks here
     *
     * @param item - the clicked navigation item
     * @return true
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                openPage(HomeFragment.class);
                break;
            case R.id.nav_map:
                openPage(MapFragment.class);
                break;
            case R.id.nav_team:
                openPage(TeamFragment.class);
                break;
            case R.id.nav_dev:
                openPage(DevFragment.class);
                break;
            case R.id.nav_web:
                openWebsite();
                break;
            case R.id.nav_fb:
                openFacebookPage();
                break;
            default:
                openPage(HomeFragment.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Open the component associated with fragment passed to it
     *
     * @param fragmentClass - Fragment to open
     */
    private void openPage(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content_container, fragment).commit();
    }

    /**
     * Open official website of Culrav
     */
    private void openWebsite() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(Config.WEBSITE));
        startActivity(Intent.createChooser(intent, getString(R.string.misc_load_with)));
    }

    /**
     * Open facebook page of Culrav
     *
     * First look if the user has facebook app installed
     * If true, open the page inside the app, otherwise provide
     * option to open the link with preferred app/browser
     */
    private void openFacebookPage() {
        final String facebookUri = "fb://page/" + Config.FB_PAGE_CULARV_ID;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(facebookUri));

        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);

        if (list.size() > 0) {
            startActivity(intent);
        } else {
            intent.setData(Uri.parse(Config.FB_PAGE_CULRAV));
            startActivity(Intent.createChooser(intent, getString(R.string.misc_load_with)));
        }
    }
}
