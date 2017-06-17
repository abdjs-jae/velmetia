package app.rocketship.natrapharmutil;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Candice on 14/02/2017.
 */

public class RequestSingleton {

    private static RequestSingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private RequestSingleton(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized RequestSingleton getInstance(Context context){

        if(instance == null){
            instance = new RequestSingleton(context);
        }

        return instance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return requestQueue;
    }

    public <T>void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }

}
