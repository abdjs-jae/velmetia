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
public class Velmetia4Fragment extends Fragment {

    GifImageView velmetia4;
    GifDrawable gif_velmetia4;

    public Velmetia4Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_velmetia4, container, false);

        velmetia4 = (GifImageView) v.findViewById(R.id.gif_velmetia4);
        try {
            gif_velmetia4 = new GifDrawable(getResources(), R.drawable.velmetia_4);
            gif_velmetia4.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        velmetia4.setImageDrawable(gif_velmetia4);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(gif_velmetia4 != null) {
            if (isVisibleToUser) {
                gif_velmetia4.start();
                gif_velmetia4.setSpeed(1.0f);
                Log.d(this.getClass().toString(), "GIF start");
            } else {
                gif_velmetia4.stop();
                Log.d(this.getClass().toString(), "GIF stop");
            }
        }
    }

}
