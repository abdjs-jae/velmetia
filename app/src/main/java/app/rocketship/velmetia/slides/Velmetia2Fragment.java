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
public class Velmetia2Fragment extends Fragment {

    GifImageView velmetia2;
    GifDrawable gif_velmetia2;

    public Velmetia2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_velmetia2, container, false);

        velmetia2 = (GifImageView) v.findViewById(R.id.gif_velmetia2);
        try {
            gif_velmetia2 = new GifDrawable(getResources(), R.drawable.velmetia_2);
            gif_velmetia2.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        velmetia2.setImageDrawable(gif_velmetia2);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(gif_velmetia2 != null) {
            if (isVisibleToUser) {
                gif_velmetia2.start();
                gif_velmetia2.setSpeed(8.0f);
                Log.d(this.getClass().toString(), "GIF start");
            } else {
                gif_velmetia2.stop();
                Log.d(this.getClass().toString(), "GIF stop");
            }
        }
    }

}
