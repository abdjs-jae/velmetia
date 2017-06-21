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
public class Velmetia5Fragment extends Fragment {

    GifImageView velmetia5;
    GifDrawable gif_velmetia5;

    public Velmetia5Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_velmetia5, container, false);

        velmetia5 = (GifImageView) v.findViewById(R.id.gif_velmetia5);
        try {
            gif_velmetia5 = new GifDrawable(getResources(), R.drawable.velmetia_5);
            gif_velmetia5.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        velmetia5.setImageDrawable(gif_velmetia5);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(gif_velmetia5 != null) {
            if (isVisibleToUser) {
                gif_velmetia5.start();
                gif_velmetia5.setSpeed(1.0f);
                Log.d(this.getClass().toString(), "GIF start");
            } else {
                gif_velmetia5.stop();
                Log.d(this.getClass().toString(), "GIF stop");
            }
        }
    }

}
