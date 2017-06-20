package info.samson.activities;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

public class SamsonApp extends Application{
	
	
	
	////USED: https://github.com/kcochibili/TinyDB--Android-Shared-Preferences-Turbo/blob/master/TinyDB.java
	
	
	public static String RENT_STARRED_LIST= "RENT_STARRED_LIST";
			public static String INFO_STARRED_LIST= "INFO_STARRED_LIST";

	private  SharedPreferences preferences;
	
	

	 @Override
	  public void onCreate()
	  {
	    super.onCreate();
	     
	    // Initialize the singletons so their instances
	    // are bound to the application process.
	    preferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());	  }
	
	public  void putListBoolean(String key, ArrayList<Boolean> marray){
		Log.i("putListBoolean", "key " + key + " marray "+ marray);

		ArrayList<String> origList = new ArrayList<String>();
		for(Boolean b : marray){
			if(b==true){
				origList.add("true");
			}else{
				origList.add("false");
			}
		}
		putList(key, origList);
	}
	
	
	public  ArrayList<Boolean> getListBoolean(String key) {
		Log.i("getListBoolean", "key " + key);

		ArrayList<String> origList = getList(key);
		ArrayList<Boolean> mBools = new ArrayList<Boolean>();
		for(String b : origList){
			if(b.equals("true")){
				mBools.add(true);
			}else{ 
				mBools.add(false);
			} 
		}
		return mBools;
	}
	
	
	public  void putList(String key, ArrayList<String> marray) {

		SharedPreferences.Editor editor = preferences.edit();
		String[] mystringlist = marray.toArray(new String[marray.size()]);
		Log.i("put string", TextUtils.join("‚‗‚", mystringlist));
		// the comma like character used below is not a comma it is the SINGLE
		// LOW-9 QUOTATION MARK unicode 201A and unicode 2017 they are used for
		// seprating the items in the list
		editor.putString(key, TextUtils.join("‚‗‚", mystringlist));
		editor.commit();
		editor.apply();
	}

	public  ArrayList<String> getList(String key) {
		// the comma like character used below is not a comma it is the SINGLE
		// LOW-9 QUOTATION MARK unicode 201A and unicode 2017 they are used for
		// seprating the items in the list
		String[] mylist = TextUtils.split(preferences.getString(key, ""), "‚‗‚");
		ArrayList<String> gottenlist = new ArrayList<String>(
				Arrays.asList(mylist));
		
		Log.i("get string", preferences.getString(key, ""));

		return gottenlist;
	}
	
	
	
}
