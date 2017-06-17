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
public class Velmetia8Fragment extends Fragment {

    GifImageView velmetia8;
    GifDrawable gif_velmetia8;

    public Velmetia8Fragment(){
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_velmetia8, container, false);

        velmetia8 = (GifImageView) v.findViewById(R.id.gif_velmetia8);
        try {
            // If it is not the first fragment, stop the GIF to save memory
            gif_velmetia8 = new GifDrawable(getResources(), R.drawable.velmetia_8);
            gif_velmetia8.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        velmetia8.setImageDrawable(gif_velmetia8);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(gif_velmetia8 != null) {
            if (isVisibleToUser) {
                gif_velmetia8.start();
                gif_velmetia8.setSpeed(8.0f);
                Log.d(this.getClass().toString(), "GIF start");
            } else {
                gif_velmetia8.stop();
                Log.d(this.getClass().toString(), "GIF stop");
            }
        }
    }

}
