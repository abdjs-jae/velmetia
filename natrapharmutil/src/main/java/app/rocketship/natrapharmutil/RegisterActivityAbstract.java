package app.rocketship.natrapharmutil;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by Candice on 14/02/2017.
 */

public abstract class RegisterActivityAbstract extends Activity {

    private EditText emailField;
    private EditText nameField;
    private EditText contactField;
    private EditText departmentField;

    private Button submitButton;

    protected abstract DataHandler.UserType getUserType();
    protected abstract Class<?> getMenuActivity();
    protected abstract Class<?> getRegisterActivity();

    protected void initializeActivity(){
        DataHandler.setCurrentContext(this);

        emailField = (EditText) findViewById(R.id.emailField);
        nameField = (EditText) findViewById(R.id.nameField);
        contactField = (EditText) findViewById(R.id.contactField);
        departmentField = (EditText) findViewById(R.id.departmentField);

        submitButton = (Button) findViewById(R.id.submitButton);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        initializeActivity();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Map<String, String> requestParams = new HashMap<>();

                requestParams.put("action", "create_user");
                requestParams.put(DataHandler.UserFields.EMAIL.getKey(), emailField.getText().toString());
                requestParams.put(DataHandler.UserFields.NAME.getKey(), nameField.getText().toString());
                requestParams.put(DataHandler.UserFields.CONTACT.getKey(), contactField.getText().toString());
                requestParams.put(DataHandler.UserFields.DEPARTMENT.getKey(), departmentField.getText().toString());
                requestParams.put(DataHandler.UserFields.USER_TYPE.getKey(), getUserType().toString());

                requestParams.put(
                        DataHandler.UserFields.DEVICE_FINGERPRINT.getKey(),
                        ((WifiManager) getSystemService(Context.WIFI_SERVICE))
                                .getConnectionInfo()
                                .getMacAddress()
                );

                DataHandler.registerUser(requestParams, getRegisterActivity(), getMenuActivity());
            }
        });
    }


}
