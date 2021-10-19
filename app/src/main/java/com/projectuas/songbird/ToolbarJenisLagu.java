package com.projectuas.songbird;

import android.content.ContentResolver;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ToolbarJenisLagu extends FragmentPagerAdapter {

    private ContentResolver contentResolver;
    private String title[] = {"Semua Lagu", "Disukai"};

    public ToolbarJenisLagu(FragmentManager fm, ContentResolver contentResolver) {
        super(fm);
        this.contentResolver = contentResolver;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SemuaLagu.getInstance(position, contentResolver);
            case 1:
                return FavoritFragment.getInstance(position);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
