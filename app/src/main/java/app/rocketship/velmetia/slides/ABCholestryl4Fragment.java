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
public class ABCholestryl4Fragment extends Fragment {

    GifImageView abc4;
    GifDrawable gif_abc4;

    public ABCholestryl4Fragment(){
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_abcholestryl4, container, false);

        abc4 = (GifImageView) v.findViewById(R.id.gif_abc4);
        try {
            // If it is not the first fragment, stop the GIF to save memory
            gif_abc4 = new GifDrawable(getResources(), R.drawable.abcholestryl_4);
            gif_abc4.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        abc4.setImageDrawable(gif_abc4);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(gif_abc4 != null) {
            if (isVisibleToUser) {
                gif_abc4.start();
                gif_abc4.setSpeed(8.0f);
                Log.d(this.getClass().toString(), "GIF start");
            } else {
                gif_abc4.stop();
                Log.d(this.getClass().toString(), "GIF stop");
            }
        }
    }

}
