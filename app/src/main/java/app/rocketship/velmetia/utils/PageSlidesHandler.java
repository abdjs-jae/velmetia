package app.rocketship.velmetia.utils;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

import app.rocketship.velmetia.R;
import app.rocketship.velmetia.slides.ABCholestryl1Fragment;
import app.rocketship.velmetia.slides.ABCholestryl2Fragment;
import app.rocketship.velmetia.slides.ABCholestryl3Fragment;
import app.rocketship.velmetia.slides.Velmetia1Fragment;
import app.rocketship.velmetia.slides.Velmetia2Fragment;
import app.rocketship.velmetia.slides.Velmetia3Fragment;
import app.rocketship.velmetia.slides.Velmetia4Fragment;
import app.rocketship.velmetia.slides.Velmetia5Fragment;

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

    public static ArrayList<Fragment> getPageSlidesFragment(Page page){

        ArrayList<Fragment> fragments = new ArrayList<>();

        switch (page){
            /*
            case ABCHOLESTRYL:


                fragments.add(new ABCholestryl1Fragment());
                fragments.add(new ABCholestryl2Fragment());
                fragments.add(new ABCholestryl3Fragment());
                fragments.add(new ABCholestryl4Fragment());
                fragments.add(new ABCholestryl5Fragment());
                fragments.add(new ABCholestryl6Fragment());
                fragments.add(new ABCholestryl7Fragment());
                fragments.add(new ABCholestryl8Fragment());


                break;
            */
            case VELMETIA:

                fragments.add(new Velmetia1Fragment());
                fragments.add(new Velmetia2Fragment());
                fragments.add(new Velmetia3Fragment());
                fragments.add(new Velmetia4Fragment());
                fragments.add(new Velmetia5Fragment());

                break;
        }

        return fragments;
    }
}
