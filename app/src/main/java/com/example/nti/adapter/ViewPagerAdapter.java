package com.example.nti.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nti.fragment.settingsFragments.AccountSettingsFragment;
import com.example.nti.fragment.settingsFragments.AppSettingsFragment;
import com.example.nti.fragment.settingsFragments.RatingSettingsFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new AccountSettingsFragment();
            case 1:
                return new RatingSettingsFragment();
            case 2:
                return new AppSettingsFragment();
        }
        return null;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Favourites";
            case 1:
                return "Rating";
            case 2:
                return "Contacts";
        }
        return super.getPageTitle(position);
    }
}
