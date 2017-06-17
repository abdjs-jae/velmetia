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
public class Velmetia1Fragment extends Fragment {

    GifImageView velmetia1;
    GifDrawable gif_velmetia1;

    public Velmetia1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_velmetia1, container, false);

        velmetia1 = (GifImageView) v.findViewById(R.id.gif_velmetia1);
        try {
            gif_velmetia1 = new GifDrawable(getResources(), R.drawable.velmetia_1);
            gif_velmetia1.setSpeed(8.0f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        velmetia1.setImageDrawable(gif_velmetia1);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(gif_velmetia1 != null) {
            if (isVisibleToUser) {
                gif_velmetia1.start();
                Log.d(this.getClass().toString(), "GIF start");
            } else {
                gif_velmetia1.stop();
                Log.d(this.getClass().toString(), "GIF stop");
            }
        }
    }

}
