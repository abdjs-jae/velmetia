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
public class ABCholestryl5Fragment extends Fragment {

    GifImageView abc5;
    GifDrawable gif_abc5;

    public ABCholestryl5Fragment(){
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_abcholestryl5, container, false);

        abc5 = (GifImageView) v.findViewById(R.id.gif_abc5);
        try {
            // If it is not the first fragment, stop the GIF to save memory
            gif_abc5 = new GifDrawable(getResources(), R.drawable.abcholestryl_5);
            gif_abc5.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        abc5.setImageDrawable(gif_abc5);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(gif_abc5 != null) {
            if (isVisibleToUser) {
                gif_abc5.start();
                gif_abc5.setSpeed(8.0f);
                Log.d(this.getClass().toString(), "GIF start");
            } else {
                gif_abc5.stop();
                Log.d(this.getClass().toString(), "GIF stop");
            }
        }
    }

}
