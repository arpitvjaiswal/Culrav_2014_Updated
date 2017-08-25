package mnnit.arpitjaiswal.culrav.y14.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mnnit.arpitjaiswal.culrav.y14.home.ExploreFragment;
import mnnit.arpitjaiswal.culrav.y14.home.NitesFragment;
import mnnit.arpitjaiswal.culrav.y14.home.StreamFragment;


public class HomeCategoriesPagerAdapter extends FragmentPagerAdapter {

    private int totalCategories = 3;

    public HomeCategoriesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ExploreFragment();
            case 1:
                return new NitesFragment();
            case 2:
                return new StreamFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return totalCategories;
    }
}
