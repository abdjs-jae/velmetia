package app.rocketship.velmetia;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class ABCholestryl2VideoActivity extends AppCompatActivity {

    VideoView videoABC;
    String current;
    String path;
    boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_abcholestryl2_video);

        path = "android.resource://"+ getPackageName() + "/" + R.raw.abcholestryl_2_video;
        videoABC = (VideoView) findViewById(R.id.abc_video);

        if (path == null || path.length() == 0) {
            Toast.makeText(ABCholestryl2VideoActivity.this, "File URL/path is empty",
                    Toast.LENGTH_LONG).show();

        } else {

            MediaController mediaController = new
                    MediaController(this);
            mediaController.setAnchorView(videoABC);
            videoABC.setMediaController(mediaController);

            if (path.equals(current) && videoABC != null) {
                videoABC.start();
                videoABC.requestFocus();
                return;
            }

            current = path;
            videoABC.setVideoPath(path);
            videoABC.start();
            videoABC.requestFocus();
        }

        videoABC.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!isPaused) {
                    videoABC.pause();
                    isPaused = true;
                } else {
                    videoABC.resume();
                    isPaused = false;
                }
            }

        });

        videoABC.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {

                finish();
                overridePendingTransition( R.anim.fade_in, R.anim.screen_splash_fade_out );

            }

        });
    }

    @Override
    public void onBackPressed() {

        finish();
        overridePendingTransition( R.anim.fade_in, R.anim.screen_splash_fade_out );

    }
}
