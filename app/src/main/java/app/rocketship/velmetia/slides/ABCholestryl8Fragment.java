package app.rocketship.velmetia.slides;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

import app.rocketship.velmetia.R;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ABCholestryl8Fragment extends Fragment {

    GifImageView abc8;
    GifDrawable gif_abc8;

    public ABCholestryl8Fragment(){
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_abcholestryl8, container, false);

        abc8 = (GifImageView) v.findViewById(R.id.gif_abc8);
        try {
            // If it is not the first fragment, stop the GIF to save memory
            // Same GIF for 5 and 8
            gif_abc8 = new GifDrawable(getResources(), R.drawable.abcholestryl_5);
            gif_abc8.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        abc8.setImageDrawable(gif_abc8);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(gif_abc8 != null) {
            if (isVisibleToUser) {
                gif_abc8.start();
                gif_abc8.setSpeed(8.0f);
                Log.d(this.getClass().toString(), "GIF start");
            } else {
                gif_abc8.stop();
                Log.d(this.getClass().toString(), "GIF stop");
            }
        }
    }

}
