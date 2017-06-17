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
public class ABCholestryl7Fragment extends Fragment {

    GifImageView abc7;
    GifDrawable gif_abc7;

    public ABCholestryl7Fragment(){
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_abcholestryl7, container, false);

        abc7 = (GifImageView) v.findViewById(R.id.gif_abc7);
        try {
            // If it is not the first fragment, stop the GIF to save memory
            gif_abc7 = new GifDrawable(getResources(), R.drawable.abcholestryl_7);
            gif_abc7.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        abc7.setImageDrawable(gif_abc7);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(gif_abc7 != null) {
            if (isVisibleToUser) {
                gif_abc7.start();
                gif_abc7.setSpeed(8.0f);
                Log.d(this.getClass().toString(), "GIF start");
            } else {
                gif_abc7.stop();
                Log.d(this.getClass().toString(), "GIF stop");
            }
        }
    }

}
