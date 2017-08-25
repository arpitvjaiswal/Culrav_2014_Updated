package mnnit.arpitjaiswal.culrav.y14.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import mnnit.arpitjaiswal.culrav.y14.R;
import mnnit.arpitjaiswal.culrav.y14.adapter.HomeCategoriesPagerAdapter;

public class HomeFragment extends Fragment {

    private MenuItem prevMenuItem;
    private static final Map<Integer, Integer> mappingNavViewItemToPagerId = new HashMap<>();

    public HomeFragment() {}

    /**
     * Add the mapping of bottom navigation view items' to viewpager fragments' position
     */
    static {
        mappingNavViewItemToPagerId.put(R.id.home_category_explore, 0);
        mappingNavViewItemToPagerId.put(R.id.home_category_nites, 1);
        mappingNavViewItemToPagerId.put(R.id.home_category_stream, 2);
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_home, container, false);

        ViewPager viewPager = (ViewPager) fragment.findViewById(R.id.home_navigation_pager);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) fragment.findViewById(R.id.home_bottom_navigation);

        setupBottomNavigationView(bottomNavigationView, viewPager);
        setupCategoriesViewPager(viewPager, bottomNavigationView);

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.home_category_explore);
        }

        // Inflate the layout for this fragment
        return fragment;
    }

    /**
     * Add listener to bottomNavigationView to open fragment from viewPager on selecting an item
     * @param bottomNavigationView
     * @param viewPager
     */
    private void setupBottomNavigationView(BottomNavigationView bottomNavigationView, final ViewPager viewPager) {
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Log.d("ARPIT", "Selected bottomView item " + item.getItemId());
                        viewPager.setCurrentItem(
                                mappingNavViewItemToPagerId.get(item.getItemId()));
                        return true;
                    }
                }
        );
    }

    /**
     * Add listener to viewPager to check bottomNavigationView's item on swiping to new page
     * Also add adapter to the pager to fetch the corresponding fragment for a page
     * @param viewPager
     * @param bottomNavigationView
     */
    private void setupCategoriesViewPager(ViewPager viewPager, final BottomNavigationView bottomNavigationView) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        HomeCategoriesPagerAdapter adapter = new HomeCategoriesPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
    }
}
