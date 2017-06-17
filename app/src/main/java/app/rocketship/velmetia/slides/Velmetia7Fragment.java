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
public class Velmetia7Fragment extends Fragment {

    GifImageView velmetia7;
    GifDrawable gif_velmetia7;

    public Velmetia7Fragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_velmetia7, container, false);

        velmetia7 = (GifImageView) v.findViewById(R.id.gif_velmetia7);
        try {
            // If it is not the frist fragment, stop the GIF to save memory
            gif_velmetia7 = new GifDrawable(getResources(), R.drawable.velmetia_7);
            gif_velmetia7.setSpeed(8.0f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        velmetia7.setImageDrawable(gif_velmetia7);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(gif_velmetia7 != null) {
            if (isVisibleToUser) {
                gif_velmetia7.start();
                Log.d(this.getClass().toString(), "GIF start");
            } else {
                gif_velmetia7.stop();
                Log.d(this.getClass().toString(), "GIF stop");
            }
        }
    }
}
