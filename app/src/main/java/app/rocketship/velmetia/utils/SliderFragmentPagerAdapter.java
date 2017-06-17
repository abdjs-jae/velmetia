package app.rocketship.velmetia.utils;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Candice on 11/02/2017.
 */



public class SliderFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> slides;


    public SliderFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public SliderFragmentPagerAdapter(FragmentManager fm, PageSlidesHandler.Page page){
        this(fm);
        slides = PageSlidesHandler.getPageSlidesFragment(page);
    }



    @Override
    public Fragment getItem(int position) {
        return slides.get(position);
    }

    @Override
    public int getCount() {
        return slides.size();
    }
}
