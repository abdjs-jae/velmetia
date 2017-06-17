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
public class ABCholestryl1Fragment extends Fragment {

    GifImageView abc1;
    GifDrawable gif_abc1;

    public ABCholestryl1Fragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_abcholestryl1, container, false);

        abc1 = (GifImageView) v.findViewById(R.id.gif_abc1);
        try {
            // If it is not the frist fragment, stop the GIF to save memory
            gif_abc1 = new GifDrawable(getResources(), R.drawable.abcholestryl_1);
            gif_abc1.setSpeed(8.0f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        abc1.setImageDrawable(gif_abc1);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(gif_abc1 != null) {
            if (isVisibleToUser) {
                gif_abc1.start();
                Log.d(this.getClass().toString(), "GIF start");
            } else {
                gif_abc1.stop();
                Log.d(this.getClass().toString(), "GIF stop");
            }
        }
    }
}
