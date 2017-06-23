// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package info.samson.activities;

import info.samson.R;
import info.samson.adaptres.Adapter;
import info.samson.fragments.InfoFrag;
import info.samson.fragments.MailFrag;
import info.samson.fragments.MapFrag;
import info.samson.fragments.RentFrag;
import info.samson.fragments.SocialFrag;
import info.samson.helpers.Constans;
import info.samson.helpers.Utils;

import android.Manifest;
import android.animation.AnimatorSet;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import static info.samson.helpers.Constans.PERMISSIONS_REQUEST_READ_CONTACTS;

/**
 * TODO
 */
public class FragmentTabs extends AppCompatActivity {

    private static final String TAG = FragmentTabs.class.getSimpleName();
    private DrawerLayout mDrawerLayout;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
        initTabLayout(viewPager);
        initFloatingBtn();

    }

    private void initFloatingBtn() {
        FloatingActionMenu materialDesignFAM;
        FloatingActionButton whatsAppBtn, callAppBtn, messangerAppBtn,skypeAppBtn;


            materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
            materialDesignFAM.setMenuButtonColorNormal(getResources().getColor(R.color.app_color));
            materialDesignFAM.setMenuButtonColorPressed(getResources().getColor(R.color.app_color_transparent));

            whatsAppBtn = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
            callAppBtn = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
            messangerAppBtn = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);
            skypeAppBtn = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item4);

            whatsAppBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Utils.openWhatsappContact("972547549913", FragmentTabs.this);
                }
            });
            callAppBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                   Utils.call(FragmentTabs.this);

                }
            });
            messangerAppBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Uri uri = Uri.parse("fb-messenger://user/");
                    uri = ContentUris.withAppendedId(uri,730267485);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });

            skypeAppBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Utils.initiateSkypeUri(FragmentTabs.this, "skype:asya.liverant?chat");
                 }
             });
        }


    private void initTabLayout(final ViewPager viewPager) {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(viewPager);

        View tab_rentals = getLayoutInflater().inflate(R.layout.custom_tab, null);
        tab_rentals.findViewById(R.id.icon).setBackgroundResource(R.drawable.rent);
        mTabLayout.getTabAt(0).setCustomView(tab_rentals);
        tab_rentals.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(0,false);
            }
        });

        View tab_mail = getLayoutInflater().inflate(R.layout.custom_tab, null);
        tab_mail.findViewById(R.id.icon).setBackgroundResource(R.drawable.mailselector);
        mTabLayout.getTabAt(1).setCustomView(tab_mail);
        tab_mail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(1,false);
            }
        });

        View tab_map = getLayoutInflater().inflate(R.layout.custom_tab, null);
        tab_map.findViewById(R.id.icon).setBackgroundResource(R.drawable.map);
//        mTabLayout.getTabAt(2).setCustomView(tab_social);
        mTabLayout.getTabAt(2).setCustomView(tab_map);//TODO
        tab_map.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(2,false);
            }
        });

        View tab_info = getLayoutInflater().inflate(R.layout.custom_tab, null);
        tab_info.findViewById(R.id.icon).setBackgroundResource(R.drawable.infoselector);
        mTabLayout.getTabAt(3).setCustomView(tab_info);
        tab_info.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(3,false);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem) {
        Intent intent;
        intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://israelrent.info/index/contacts/0-12"));
        startActivity(intent);
        return true;
    }


    private void setupViewPager(final ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new RentFrag(), "Category 1");
        adapter.addFragment(new MailFrag(), "Category 2");
        adapter.addFragment(new MapFrag(), "Category 3");
//        adapter.addFragment(new SocialFrag(), "Category 3");
        adapter.addFragment(new InfoFrag(), "Category 4");
        viewPager.setAdapter(adapter);
        viewPager.removeOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position, true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}

//import android.os.Bundle;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.support.design.widget.TabLayout;
//import android.widget.Adapter;
//
//import info.samson.R;
//
//public class FragmentTabs  extends AppCompatActivity
//{
//
//    TabLayout tabLayout;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setupViewPager();
//        setupTablayout();
////        setupFab();
//
//    }
//
//    private void setupTablayout(){
//
//        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 4"));
//    }
//
//    private void setupViewPager() {
//
//        Adapter adapter = new Adapter(getSupportFragmentManager());
//        adapter.addFragment(new CheeseListFragment(), "Category 1");
//        adapter.addFragment(new CheeseListFragment(), "Category 2");
//        adapter.addFragment(new CheeseListFragment(), "Category 3");3
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//
//    }
//
//
//
//    private void setupToolbar(){
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        // Show menu icon
//        final ActionBar ab = getSupportActionBar();
//        //ab.setHomeAsUpIndicator(R.drawable.ic_menu);
//        ab.setDisplayHomeAsUpEnabled(true);
//    }
//}
//
////    //private FragmentTabHost mTabHost;
////  //  private TabLayout mTabLayout;
////    public FragmentTabs()
////    {
////    }
////
////    protected void onCreate(Bundle bundle)
////    {
////        super.onCreate(bundle);
////        //setContentView(R.layout.activity_main);
//////        mTabLayout = (TabLayout)findViewById(R.id.sliding_tabs);
//////		mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
//////		mTabHost.setup(this, getSupportFragmentManager(),  R.id.realtabcontent);
//////
//////		mTabHost.addTab(mTabHost.newTabSpec("rent").setIndicator("", getResources().getDrawable(R.drawable.rent)),
//////				RentFrag.class, null);
//////		mTabHost.addTab(mTabHost.newTabSpec("mail").setIndicator("", getResources().getDrawable(R.drawable.mailselector)),
//////				MailFrag.class, null);
//////		mTabHost.addTab(mTabHost.newTabSpec("map").setIndicator("",  getResources().getDrawable(R.drawable.map)),
//////				MapFrag.class, null);
//////		mTabHost.addTab(mTabHost.newTabSpec("social").setIndicator("",  getResources().getDrawable(R.drawable.socialselector)),
//////				SocialFrag.class, null);
//////		mTabHost.addTab(mTabHost.newTabSpec("info").setIndicator("",  getResources().getDrawable(R.drawable.infoselector)),
//////				InfoFrag.class, null);
//////		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
//////			@Override
//////			public void onTabChanged(String tabId) {
//////			    Log.v("onTabChanged", tabId);
//////			        FragmentManager manager = getSupportFragmentManager();
//////			        Fragment fr = manager.findFragmentByTag(tabId);
//////			        if(fr != null){
//////			            Log.v("Fragment", "TRANSACTION");
//////			            FragmentTransaction ft  =   manager.beginTransaction();
//////			            ft.setCustomAnimations(R.anim.left, R.anim.left);
//////			           // ft.replace(R.id.realtabcontent, ...); //don't know how to get next fragment
//////			            ft.commit();
//////			        }
//////			}
//////		});
////    }
////
////
////
////    public boolean onCreateOptionsMenu(Menu menu)
////    {
////  //      getMenuInflater().inflate(R.menu.main, menu);
////        return true;
////    }
////
////    public boolean onOptionsItemSelected(MenuItem menuitem)
////    {
////        Intent intent;
////        //        switch (menuitem.getItemId())
////        //        {
////        //        default:
////        //            Log.e("LOG", (new StringBuilder()).append(menuitem.getItemId()).toString());
////        //            return super.onOptionsItemSelected(menuitem);
////        //
////        //        case 2131230736:
////        //            intent = new Intent("android.intent.action.VIEW");
////        //            break;
////        //        }
////        intent = new Intent("android.intent.action.VIEW");
////
////        intent.setData(Uri.parse("http://israelrent.info/index/contacts/0-12"));
////        startActivity(intent);
////        return true;
////    }
////}
