package app.rocketship.natrapharmutil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Candice on 14/02/2017.
 */

public class ActivityHandler {

    private static Class<?> menuClass;

    private static void changeActivity(Context changeFrom, Class<?> changeTo){
        Intent i = new Intent(changeFrom, changeTo);
        changeFrom.startActivity(i);

        ((Activity) changeFrom).finish();
        ((Activity) changeFrom).overridePendingTransition(0, R.anim.screen_splash_fade_out);

    }

    public static void goToProfile(Context currentContext){
        Intent i = new Intent(currentContext, ProfileActivity.class);
        currentContext.startActivity(i);

        ((Activity) currentContext).finish();
        ((Activity) currentContext).overridePendingTransition(0, R.anim.screen_splash_fade_out);
    }

    public static void goToMenu(Context currentContext){
        Intent i = new Intent(currentContext, menuClass);
        currentContext.startActivity(i);

        ((Activity) currentContext).finish();
        ((Activity) currentContext).overridePendingTransition(0, R.anim.screen_splash_fade_out);
    }

    public static void goHome(Context context){

        ((Activity) context).finish();

    }

    public static void refreshActivity(Context context){
        ((Activity) context).finish();
        context.startActivity(((Activity) context).getIntent());
    }

    public static Class<?> getMenuClass(){
        return menuClass;
    }

    public static void setMenuClass(Class<?> landingClass){
        menuClass = landingClass;
    }

    public static void afterSplashActivity(final Context splashActivity, final Class<?> registerClass, final Class<?> localMenuClass){

        DataHandler.setCurrentContext(splashActivity);

        if(DataHandler.hasUserData()) {

            setMenuClass(localMenuClass);
            changeActivity(splashActivity, localMenuClass);

        }
        else
            DataHandler.isDeviceRegistered(
                    new DataHandler.VolleyCallback() {
                        @Override
                        public void doAction(String result) {
                            Log.d("Test", "device exists: " + result);
                            DataHandler.setUserData(result);

                            setMenuClass(localMenuClass);
                            changeActivity(splashActivity, localMenuClass);

                            /* So instead of changing the activity directly to 'menuClass',
                            I want you to change it to LandingActivity.
                            Put a public static Class<?> menuClass in LandingActivity and set it to this method's 'menuClass'

                            Whenever the menu button is clicked from LandingActivity, call ActivityHandler.changeActivity(landingActivity, menuClass) 

                            */

                        }
                    },
                    new DataHandler.VolleyCallback() {
                        @Override
                        public void doAction(String result) {
                            changeActivity(splashActivity, registerClass);
                        }
                    }
            );
//            changeActivity(splashActivity, registerClass);


    }
}
