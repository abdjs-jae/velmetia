package app.rocketship.natrapharmutil;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    TextView tvUsername;
    TextView tvDepartment;
    TextView tvEmail;
    TextView tvUserEmail;
    TextView tvContact;
    TextView tvUserContact;

    ImageView buttonBack;

    ArrayList<String> listUserData = new ArrayList<>();

    public enum UserData {

        NAME(0),
        DEPARTMENT(1),
        EMAIL(2),
        CONTACT(3);

        UserData (int i)
        {
            this.id = i;
        }

        private int id;

        public int getNumericType()
        {
            return id;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        // Set title of Detail page
        collapsingToolbar.setTitle("Profile");

        // CHANGEABLE TEXTVIEWS
        tvUsername = (TextView) findViewById(R.id.tv_profile_name);
        tvDepartment = (TextView) findViewById(R.id.tv_profile_desc);
        tvUserEmail = (TextView) findViewById(R.id.tv_user_email);
        tvUserContact = (TextView) findViewById(R.id.tv_user_contact);

        // PERMANENT TEXTVIEWS
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvContact = (TextView) findViewById(R.id.tv_contact);
        buttonBack = (ImageView) findViewById(R.id.iv_back);

        listUserData = DataHandler.getUserData();

        // Set data to TextViews
        tvUsername.setText(listUserData.get(UserData.NAME.getNumericType()));
        tvDepartment.setText(listUserData.get(UserData.DEPARTMENT.getNumericType()));
        tvUserEmail.setText(listUserData.get(UserData.EMAIL.getNumericType()));
        tvUserContact.setText(listUserData.get(UserData.CONTACT.getNumericType()));

        // Set listeners
        buttonBack.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ActivityHandler.goToMenu(ProfileActivity.this);
            }

        });
    }
}
