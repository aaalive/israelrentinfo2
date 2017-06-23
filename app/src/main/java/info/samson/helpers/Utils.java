package info.samson.helpers;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import info.samson.activities.FragmentTabs;

public class Utils {
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
             
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              //Read byte from input stream
                 
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
               
              //Write byte from output stream
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
    
    public static boolean isAppInstalled(String uri, PackageManager packageManager) {
        boolean oIsInstalled = false;
        try {
            packageManager.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            oIsInstalled = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            oIsInstalled = false;
        }
        return oIsInstalled ;
    }

    public static void openWhatsappContact(String s, Activity activity)
    {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");

        PackageManager pm = activity.getApplicationContext().getPackageManager();
        final List<ResolveInfo> matches = pm.queryIntentActivities(sendIntent, 0);
        boolean temWhatsApp = false;
        for (final ResolveInfo info : matches) {
            if (info.activityInfo.packageName.startsWith("com.whatsapp"))  {
                final ComponentName name = new ComponentName(info.activityInfo.applicationInfo.packageName, info.activityInfo.name);
                sendIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                sendIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
                sendIntent.setComponent(name);
                temWhatsApp = true;
                break;
            }
        }
        if(temWhatsApp){
            Intent intent1 = new Intent("android.intent.action.SENDTO", Uri.parse((new StringBuilder("smsto:")).append(s).toString()));
            intent1.setPackage("com.whatsapp");
            activity.startActivity(Intent.createChooser(intent1, ""));
        }
        else{
            Toast.makeText(activity, "\u041F\u043E\u0436\u0430\u043B\u0443\u0439\u0441\u0442\u0430 \u0443\u0441\u0442\u0430\u043D\u043E\u0432\u0438\u0442\u0435 whatsapp", Toast.LENGTH_LONG);
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp"));
            activity.startActivity(intent2);
        }
    }

    public static void checkPermissions(Activity activity){
        if (ContextCompat.checkSelfPermission(activity,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_CONTACTS}, Constans.PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            Utils.openViber("972547549913", activity);
        }
    }

    private static  Uri getUriFromPhoneNumber(String phoneNumber, Context context) {
        Uri uri = null;

            String contactId = getContactIdByPhoneNumber(phoneNumber, context);
            if (!TextUtils.isEmpty(contactId)) {
                Cursor cursor = context.getContentResolver().query(
                        ContactsContract.Data.CONTENT_URI, new String[]{ContactsContract.Data._ID},
                        ContactsContract.Data.DATA2 + "=? AND " + ContactsContract.Data.CONTACT_ID + " = ?",
                        new String[]{"Viber", contactId}, null);
                if (cursor != null) {
                    while (cursor.moveToNext()){
                        String id = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Data._ID));
                        if (!TextUtils.isEmpty(id)) {
                            uri = Uri.parse(ContactsContract.Data.CONTENT_URI + "/" + id);
                            break;
                        }
                    }
                    cursor.close();
                }
            }
        return uri;
    }

    private static String getContactIdByPhoneNumber(String phoneNumber, Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String contactId = null;
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));

        String[] projection = new String[]{ContactsContract.PhoneLookup._ID};

        Cursor cursor =
                contentResolver.query(
                        uri,
                        projection,
                        null,
                        null,
                        null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
            }
            cursor.close();
        }
        return contactId;
    }
    public static void openViber(String s, Activity activity) {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setPackage("com.viber.voip");
        share.setType("text/plain");
        share.setData(Uri.parse("sms:+972547549913"));
//        share.setData( getUriFromPhoneNumber("+972549138914",activity));
        share.putExtra(Intent.EXTRA_TEXT, "Your text to share");
        activity.startActivity(share);
    }

    public static void call(Activity activity) {
        if (((TelephonyManager)activity.getSystemService(Context.TELEPHONY_SERVICE)).getPhoneType() == 0)
        {
            Toast.makeText(activity, "\u0422\u0435\u043B\u0435\u0444\u043E\u043D\u043D\u044B\u0435 \u0437\u0432\u043E\u043D\u043A\u0438 \u043D\u0435 \u043F\u043E\u0434\u0434\u0435\u0440\u0436\u0438\u0432\u0430\u044E\u0442\u0441\u044F", 1);
            Intent intent1 = new Intent("android.intent.action.VIEW");
            intent1.setData(Uri.parse("http://israelrent.info/index/contacts/0-12"));
            activity.startActivity(intent1);

        } else
        {
            callPhone("+972547549913", activity);
        }
    }

    private static void callPhone(String s, Activity activity) {
        activity.startActivity(new Intent("android.intent.action.CALL", Uri.parse((new StringBuilder("tel:")).append(s).toString())));
    }

    public static void initiateSkypeUri(Activity activity, String s)
    {

        if (!isSkypeClientInstalled(activity))
        {
            goToMarket(activity);
            return;
        } else
        {
//            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(s));
//            intent.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            activity.startActivity(intent);
//            return;
            Intent sky = new Intent("android.intent.action.VIEW");
            sky.setData(Uri.parse(s));
            activity.startActivity(sky);
        }
    }

    /**
     * Determine whether the Skype for Android client is installed on this device.
     */
    private static boolean isSkypeClientInstalled(Context myContext) {
        PackageManager myPackageMgr = myContext.getPackageManager();
        try {
            myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES);
        }
        catch (PackageManager.NameNotFoundException e) {
            return (false);
        }
        return (true);
    }


    private static  void goToMarket(Context context)
    {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.skype.raider"));
        context.startActivity(intent);
    }
}