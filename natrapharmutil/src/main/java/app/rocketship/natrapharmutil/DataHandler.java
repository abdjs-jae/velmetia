package app.rocketship.natrapharmutil;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import app.rocketship.natrapharmutil.sqlite.SQLHelper;
import app.rocketship.natrapharmutil.sqlite.SQLiteSingleton;


/**
 * Created by Candice on 13/02/2017.
 */

public class DataHandler {

    private final static int CONNECTION_TIMEOUT = 10000;
    private final static int READ_TIMEOUT = 10000;

    private final static String PREFERENCE_FILE_KEY = "user_data_file";

    public enum UserType{
        ADMIN("admin"), USER("user");

        private String userType;

        UserType(String userType) {
            this.userType = userType;
        }

        public String toString(){
            return userType;
        }

    }

    public enum UserFields{
        NAME("name"),
        EMAIL("email"),
        CONTACT("contact_number"),
        DEPARTMENT("department"),
        USER_TYPE("user_type"),
        DEVICE_FINGERPRINT("device_fingerprint");

        private String key;

        UserFields(String key){
            this.key = key;
        }

        public String getKey(){
            return key;
        }
    }

    private static Context currentContext;
    private static boolean hasConnection;

    public static boolean hasUserData(){
        SharedPreferences data = currentContext.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);

        return data.getString(UserFields.DEVICE_FINGERPRINT.getKey(), null) != null;
    }

    public static ArrayList<String> getUserData(){

        ArrayList<String> listUser = new ArrayList<>();

        SharedPreferences data = currentContext.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);

        // Add all user info
        listUser.add(data.getString(UserFields.NAME.getKey(), null));
        listUser.add(data.getString(UserFields.DEPARTMENT.getKey(), null));
        listUser.add(data.getString(UserFields.EMAIL.getKey(), null));
        listUser.add(data.getString(UserFields.CONTACT.getKey(), null));

        return listUser;
    }

    public static void setUserData(Map<String, String> params){
        SharedPreferences data = currentContext.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = data.edit();

        Iterator i =  params.entrySet().iterator();

        while(i.hasNext()){
            Map.Entry pair = (Map.Entry)i.next();
            editor.putString((String)pair.getKey(), (String)pair.getValue());
            i.remove();
        }

        editor.commit();

    }

    public static void setUserData(String params){
        try {
            JSONObject paramJson = new JSONObject(params);

            SharedPreferences data = currentContext.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = data.edit();

            Iterator i = paramJson.keys();

            while(i.hasNext()){
                String key = (String) i.next();
                editor.putString(key, paramJson.getString(key));
                i.remove();
            }

            editor.commit();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static void setCurrentContext(Context context){
        currentContext = context;
    }

    public static void isDeviceRegistered(final VolleyCallback deviceExists, final VolleyCallback notDeviceExists){
        StringRequest request = new StringRequest(
                Request.Method.POST,
                currentContext.getString(R.string.request_url),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Test", "isDeviceRegistered(): " + response);

                        try {
                            JSONObject responseJson = new JSONObject(response);

                            if(responseJson.getInt("success") == 1){
                                if(responseJson.getInt("device_exists") == 1){
                                    deviceExists.doAction(responseJson.getJSONObject("device").toString());
                                }else{
                                    notDeviceExists.doAction(response);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if(error instanceof NoConnectionError){
                            Log.d("Test", "No connection");
                            Alert.connectionNeededDevice();
                        }else if(error instanceof TimeoutError) {
                            Log.d("Test", "Timeout Error");
                            Alert.connectionTimeout();
                        }else{
                            Log.d("Test", "errrooorrrr");
                            error.printStackTrace();
                        }
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> param = new HashMap<>();

                param.put("action", "is_device_exists");
                param.put("fingerprint",
                        ((WifiManager) currentContext.getSystemService(Context.WIFI_SERVICE))
                                .getConnectionInfo()
                                .getMacAddress()
                );

                return param;
            }
        };



        RequestSingleton.getInstance(currentContext).addToRequestQueue(request);
    }

    public static void registerUser(final Map<String, String> params, final Class<?> registerActivity, final Class<?> menuActivity) {

        StringRequest request = new StringRequest(
                Request.Method.POST,
                currentContext.getString(R.string.request_url),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Test", response);

                        try {
                            JSONObject responseJson = new JSONObject(response);
                            if( responseJson.getInt("success") == 1 ){
                                setUserData(params);
                                ActivityHandler.afterSplashActivity(currentContext, registerActivity, menuActivity);

                            }else{
                                Alert.oops();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Alert.oops();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        if(error instanceof NoConnectionError){
                            Alert.connectionNeededRegistration();
                        }else{
                            Log.d("Test", "errrooorrrr");
                            error.printStackTrace();
                        }
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                return params;
            }
        };

        RequestSingleton.getInstance(currentContext).addToRequestQueue(request);

    }

    public static void setNetworkConnection(){
        hasConnection = hasNetworkConnection();
    }

    public static boolean hasNetworkConnection(){
        NetworkInfo networkInfo =
                ((ConnectivityManager)currentContext.getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();

    }

    public static void savePageClickLocal(String pageKey){
        SQLiteDatabase db = SQLiteSingleton.getInstance(currentContext).getDatabase(SQLiteSingleton.DBMode.WRITE);

        ContentValues values = new ContentValues();
        values.put(SQLHelper.ClickColumns.PAGE_KEY.getColumnName(), pageKey);
//        values.put(SQLHelper.ClickColumns.TIMESTAMP.getColumnName(), "Default");

        db.insert(SQLHelper.Tables.CLICKS.getTableName(), null, values);
    }

    public static void savePageClickServer(final String pageKey){


        StringRequest request = new StringRequest(
                Request.Method.POST,
                currentContext.getString(R.string.request_url),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Test", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                        Log.d("Test", "No click save server");
                        savePageClickLocal(pageKey);

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){

                SharedPreferences data = currentContext.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);

                String fingerprintKey = UserFields.DEVICE_FINGERPRINT.getKey();


                Map<String, String> param = new HashMap<>();
                param.put("action", "record_click");
                param.put("page_key", pageKey);

                param.put(
                        fingerprintKey,
                        data.getString(fingerprintKey, null)
                );

                return param;
            }
        };


        RequestSingleton.getInstance(currentContext).addToRequestQueue(request);
    }

    public static void savePageClick(String pageKey){
//        if(hasConnection)
//            savePageClickServer(pageKey);
//        else
//            savePageClickLocal(pageKey);


        savePageClickServer(pageKey);

    }

    public static void printClicks(){

        SQLiteDatabase db = SQLiteSingleton.getInstance(currentContext).getDatabase(SQLiteSingleton.DBMode.READ);

        String[] columns = {
                SQLHelper.ClickColumns.PAGE_KEY.getColumnName(),
                SQLHelper.ClickColumns.TIMESTAMP.getColumnName()
        };


        Cursor cursor = db.query(
                SQLHelper.Tables.CLICKS.getTableName(),
                columns,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            int i = cursor.getColumnIndex(SQLHelper.ClickColumns.PAGE_KEY.getColumnName());
            int j = cursor.getColumnIndex(SQLHelper.ClickColumns.TIMESTAMP.getColumnName());

            Log.d("Test", "Page_key: " + cursor.getString(i) );
            Log.d("Test", "Timestamp: " + cursor.getString(j));
        }

        cursor.close();

    }

    private static class Alert{

        private static void oops(){

            new AlertDialog.Builder(currentContext)
                    .setTitle("Oops! Something went wrong")
                    .setMessage("There seems to be some trouble. Please try again in a few minutes :(")
                    .show();
        }

        private static void connectionNeededDevice(){
            new AlertDialog.Builder(currentContext)
                    .setTitle("Connection Needed!")
                    .setMessage("A connection is needed once after installing the application. Please make sure you're connected to wifi or data to proceed.")
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityHandler.refreshActivity(currentContext);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityHandler.goHome(currentContext);
                        }
                    })
                    .show();
        }

        private static void connectionTimeout(){
            new AlertDialog.Builder(currentContext)
                    .setTitle("Connection Timeout")
                    .setMessage("A connection is needed once after installing the application. Please make sure you do not have slow/unreliable internet connection.")
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityHandler.refreshActivity(currentContext);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityHandler.goHome(currentContext);
                        }
                    })
                    .show();
        }

        private static void connectionNeededRegistration(){
            new AlertDialog.Builder(currentContext)
                    .setTitle("Connection Needed!")
                    .setMessage("A connection is needed for the one-time registration. Note: You will only be required to register once.")
                    .show();
        }

    }

    public interface VolleyCallback{
        public void doAction(String result);
    }

}
