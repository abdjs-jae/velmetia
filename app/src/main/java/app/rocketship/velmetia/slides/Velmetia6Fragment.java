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
public class Velmetia6Fragment extends Fragment {

    GifImageView velmetia6;
    GifDrawable gif_velmetia6;

    public Velmetia6Fragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_velmetia6, container, false);

        velmetia6 = (GifImageView) v.findViewById(R.id.gif_velmetia6);
        try {
            // If it is not the frist fragment, stop the GIF to save memory
            gif_velmetia6 = new GifDrawable(getResources(), R.drawable.velmetia_6);
            gif_velmetia6.setSpeed(8.0f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        velmetia6.setImageDrawable(gif_velmetia6);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(gif_velmetia6 != null) {
            if (isVisibleToUser) {
                gif_velmetia6.start();
                Log.d(this.getClass().toString(), "GIF start");
            } else {
                gif_velmetia6.stop();
                Log.d(this.getClass().toString(), "GIF stop");
            }
        }
    }
}
