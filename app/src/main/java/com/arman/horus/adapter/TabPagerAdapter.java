package com.arman.horus.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.arman.horus.tabs.BoardTab;
import com.arman.horus.tabs.MapTab;
import com.arman.horus.tabs.PlansTab;
import com.arman.horus.tabs.ProfileTab;

/**
 * Created by arman on 3/24/16.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Fragment tab1 = new BoardTab();
                return tab1;
            case 1:
                Fragment tab2 = new MapTab();
                return tab2;
            case 2:
                Fragment tab3 = new PlansTab();
                return tab3;
            case 3:
                Fragment tab4 = new ProfileTab();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
