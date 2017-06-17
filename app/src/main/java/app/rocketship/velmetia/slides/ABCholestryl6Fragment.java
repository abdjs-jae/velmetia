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
public class ABCholestryl6Fragment extends Fragment {

    GifImageView abc6;
    GifDrawable gif_abc6;

    public ABCholestryl6Fragment(){
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_abcholestryl6, container, false);

        abc6 = (GifImageView) v.findViewById(R.id.gif_abc6);
        try {
            // If it is not the first fragment, stop the GIF to save memory
            gif_abc6 = new GifDrawable(getResources(), R.drawable.abcholestryl_6);
            gif_abc6.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        abc6.setImageDrawable(gif_abc6);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(gif_abc6 != null) {
            if (isVisibleToUser) {
                gif_abc6.start();
                gif_abc6.setSpeed(8.0f);
                Log.d(this.getClass().toString(), "GIF start");
            } else {
                gif_abc6.stop();
                Log.d(this.getClass().toString(), "GIF stop");
            }
        }
    }

}
