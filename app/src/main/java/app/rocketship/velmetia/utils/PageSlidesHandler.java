package app.rocketship.velmetia.utils;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

import app.rocketship.velmetia.R;
import app.rocketship.velmetia.slides.Velmetia1Fragment;
import app.rocketship.velmetia.slides.Velmetia2Fragment;
import app.rocketship.velmetia.slides.Velmetia6Fragment;
import app.rocketship.velmetia.slides.Velmetia7Fragment;
import app.rocketship.velmetia.slides.Velmetia8Fragment;

/**
 * Created by Jason on 09/03/2017.
 */

public class PageSlidesHandler {
    public enum Page{
        VELMETIA("velmetia", R.string.velmetia);

        private String key;
        private int labelId;
        Page(String key, int labelId){
            this.key = key;
            this.labelId = labelId;
        }

        public String getKey(){
            return key;
        }
        public int getLabelId() {
            return labelId;
        }
    }

    /*
        Handles the slides / fragments in the slideshow viewer.
     */
    public static ArrayList<Fragment> getPageSlidesFragment(Page page){

        ArrayList<Fragment> fragments = new ArrayList<>();

        switch (page){
            /*
            case ABCHOLESTRYL:


                fragments.add(new Velmetia6Fragment());
                fragments.add(new Velmetia7Fragment());
                fragments.add(new Velmetia8Fragment());
                fragments.add(new ABCholestryl4Fragment());
                fragments.add(new ABCholestryl5Fragment());
                fragments.add(new ABCholestryl6Fragment());
                fragments.add(new ABCholestryl7Fragment());
                fragments.add(new ABCholestryl8Fragment());


                break;
            */
            case VELMETIA:

                // NOTE:
                // Velmetia 3,4,5 are subpages for Velmetia2Fragment,
                // thus they cannot be a part of the whole slideshow.
                fragments.add(new Velmetia1Fragment());
                fragments.add(new Velmetia2Fragment());
                fragments.add(new Velmetia6Fragment());
                fragments.add(new Velmetia7Fragment());
                fragments.add(new Velmetia8Fragment());

                break;
        }

        return fragments;
    }
}
