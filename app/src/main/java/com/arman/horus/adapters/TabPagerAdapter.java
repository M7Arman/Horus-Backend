package com.arman.horus.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.arman.horus.fragments.BoardTabFragment;
import com.arman.horus.fragments.MapTabFragment;
import com.arman.horus.fragments.PlansTabFragment;
import com.arman.horus.fragments.ProfileTabFragment;

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
                return new BoardTabFragment();
            case 1:
                return new MapTabFragment();
            case 2:
                return new PlansTabFragment();
            case 3:
                return new ProfileTabFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
