package app.rocketship.velmetia.slides;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.rocketship.velmetia.ABCholestryl2VideoActivity;
import app.rocketship.velmetia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ABCholestryl2Fragment extends Fragment {

    Button buttonPlayVideo;

    public ABCholestryl2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_abcholestryl2, container, false);

        buttonPlayVideo = (Button) v.findViewById(R.id.button_playvideo);

        buttonPlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ABCholestryl2VideoActivity.class);
                startActivity(i);
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

}
