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
public class Velmetia3Fragment extends Fragment {

    GifImageView velmetia3;
    GifDrawable gif_velmetia3;

    public Velmetia3Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_velmetia3, container, false);

        velmetia3 = (GifImageView) v.findViewById(R.id.gif_velmetia3);
        try {
            gif_velmetia3 = new GifDrawable(getResources(), R.drawable.velmetia_3);
            gif_velmetia3.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        velmetia3.setImageDrawable(gif_velmetia3);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(gif_velmetia3 != null) {
            if (isVisibleToUser) {
                gif_velmetia3.start();
                gif_velmetia3.setSpeed(8.0f);
                Log.d(this.getClass().toString(), "GIF start");
            } else {
                gif_velmetia3.stop();
                Log.d(this.getClass().toString(), "GIF stop");
            }
        }
    }

}
