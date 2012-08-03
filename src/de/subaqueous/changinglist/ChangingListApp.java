package de.subaqueous.changinglist;

import android.app.Application;
import android.content.Context;

public class ChangingListApp extends Application {
	
	private static Context context;
	
	   public void onCreate(){
	        super.onCreate();
	        ChangingListApp.context = getApplicationContext();
	   }
	
    public static Context getAppContext() {
        return ChangingListApp.context;
    }

}
