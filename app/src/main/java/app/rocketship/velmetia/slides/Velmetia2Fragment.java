package app.rocketship.velmetia.slides;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;

import app.rocketship.velmetia.MainActivity;
import app.rocketship.velmetia.R;
import app.rocketship.velmetia.Velmetia3Activity;
import app.rocketship.velmetia.Velmetia4Activity;
import app.rocketship.velmetia.Velmetia5Activity;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Velmetia2Fragment extends Fragment {

    GifImageView velmetia2;
    GifDrawable gif_velmetia2;
    Button b_left_heart;
    Button b_mid_heart;
    Button b_kidney;


    // Here are the boolean functions to start you with the functionality.
    boolean velmetia3Visited = false;
    boolean velmetia4Visited = false;

    /*
        Here, we need to create 3 views for the onClickListener linking to come in here.
        NOTE!
        At this point, slide 2 cannot go to slide 4 and 5 directly without going to slide 3.
        Same goes for the others, they need to pass all the slides before them.
     */


    public Velmetia2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_velmetia2, container, false);

        velmetia2 = (GifImageView) v.findViewById(R.id.gif_velmetia2);
        b_left_heart = (Button) v.findViewById(R.id.left_heart);
        b_mid_heart = (Button) v.findViewById(R.id.mid_heart);
        b_kidney = (Button) v. findViewById(R.id.right_kidney);


        //change to page 3
        b_left_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                velmetia3Visited = true;
                Intent i = new Intent(getActivity(), Velmetia3Activity.class);
                startActivity(i);
            }
        });

        //change to page 4
        b_mid_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(velmetia3Visited){
                    velmetia4Visited = true;
                    Intent i = new Intent(getActivity(), Velmetia4Activity.class);
                    startActivity(i);
                    //change to the new activity
                }else{
                    AlertDialog ad = new AlertDialog.Builder(getContext())
                            .create();
                    ad.setCancelable(false);
                    ad.setTitle("Ooops!");
                    ad.setMessage("You must first read the section about 'No increase in risk for CV events'");
                    ad.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ad.show();
                }
            }
        });
        //change to page 5
        b_kidney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(velmetia4Visited){
                    //change to the new activity
                    Intent i = new Intent(getActivity(), Velmetia5Activity.class);
                    startActivity(i);
                }else{
                    AlertDialog ad = new AlertDialog.Builder(getContext())
                            .create();
                    ad.setCancelable(false);
                    ad.setTitle("Ooops!");
                    ad.setMessage("You must first read the sections about 'No increase in risk for CV events' and 'No increase in risk for HF'");
                    ad.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ad.show();
                }
            }
        });

        try {
            gif_velmetia2 = new GifDrawable(getResources(), R.drawable.velmetia_2);
            gif_velmetia2.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        velmetia2.setImageDrawable(gif_velmetia2);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(gif_velmetia2 != null) {
            if (isVisibleToUser) {
                gif_velmetia2.start();
                gif_velmetia2.setSpeed(1.0f);
                Log.d(this.getClass().toString(), "GIF start");
            } else {
                gif_velmetia2.stop();
                Log.d(this.getClass().toString(), "GIF stop");
            }
        }
    }

}
