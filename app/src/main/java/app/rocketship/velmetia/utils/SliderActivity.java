package app.rocketship.velmetia.utils;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.rocketship.natrapharmutil.DataHandler;
import app.rocketship.natrapharmutil.sqlite.SQLiteSingleton;
import app.rocketship.velmetia.MainActivity;
import app.rocketship.velmetia.R;

public class SliderActivity extends AppCompatActivity {

    private PagerAdapter pagerAdapter;
    private ViewPager sliderViewPager;
    private TabLayout tabLayout;

    PercentRelativeLayout prlSlider;
    LinearLayout logoContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        sliderViewPager = (ViewPager) findViewById(R.id.sliderViewPager);
        tabLayout = (TabLayout) findViewById(R.id.sliderdotlayout);
        logoContainer = (LinearLayout) findViewById(R.id.linlay_logocontainer);

        DataHandler.setNetworkConnection();

        pagerAdapter = new SliderFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.selectedPage);
        sliderViewPager.setAdapter(pagerAdapter);
        sliderViewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(sliderViewPager, true);
    }

    // Launches the slideshow itself.

    private void switchSliders(PageSlidesHandler.Page page){

        DataHandler.setCurrentContext(this);
        DataHandler.savePageClick(page.getKey());

        MainActivity.selectedPage = page;

        Intent i = new Intent(this, SliderActivity.class);

        startActivity(i);
        finish();
        overridePendingTransition(0, R.anim.fade_in);

    }

    private void setFooter(PageSlidesHandler.Page page){
        switch(page){
            /*
            case ABCHOLESTRYL: {
                // red orange
                TextView tvABC = new TextView(getBaseContext());
                tvABC.setText("AB CHOLESTRYL");
                tvABC.setTextColor(Color.parseColor("#ffffff"));
                tvABC.setTextSize(30.0f);
                logoContainer.addView(tvABC);
                prlSlider.setBackgroundColor(Color.parseColor("#f24d10")); break;
            }
            */
            case VELMETIA:{
                // light blue
                /*
                ImageView ivVelmetia = new ImageView(getBaseContext());
                ivVelmetia.setImageResource(R.drawable.velmetia_footer);
                ivVelmetia.setPadding(3, 5, 5, 3);
                ImageView ivXelevia = new ImageView(getBaseContext());
                ivXelevia.setImageResource(R.drawable.xelevia_footer);
                ivXelevia.setPadding(3, 5, 5, 3);
                logoContainer.addView(ivVelmetia);
                logoContainer.addView(ivXelevia);
                */
                //prlSlider.setBackgroundColor(Color.parseColor("#2db5ea"));
                break;
            }
        }
    }

    private void setFooterColor(PageSlidesHandler.Page page){
        switch(page){
            /*
            case ABCHOLESTRYL: {
                // red orange
                prlSlider.setBackgroundColor(Color.parseColor("#f24d10")); break;
            }

            case VELMETIA:{
                // light blue
                prlSlider.setBackgroundColor(Color.parseColor("#2db5ea")); break;
            }
            */
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SliderActivity.this, MainActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition( 0, R.anim.fade_in );
    }

    @Override
    protected void onDestroy(){

        SQLiteSingleton.getInstance(this).close();

        super.onDestroy();
    }

}
