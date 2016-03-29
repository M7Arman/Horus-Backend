package com.arman.horus.adapters;

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
                return new BoardTab();
            case 1:
                return new MapTab();
            case 2:
                return new PlansTab();
            case 3:
                return new ProfileTab();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
