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
import info.samson.helpers.Utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public class FragmentTabs extends AppCompatActivity {

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
        FloatingActionButton whatsAppBtn, viberAppBtn, floatingActionButton3;


            materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
            whatsAppBtn = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
            viberAppBtn = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
            floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);

            whatsAppBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Utils.openWhatsappContact("972547549913", FragmentTabs.this);
                }
            });
            viberAppBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Utils.openViber("972547549913", FragmentTabs.this);
                }
            });
            floatingActionButton3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                }
            });
        }


    private void initTabLayout(ViewPager viewPager) {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(viewPager);

        View tab_rentals = getLayoutInflater().inflate(R.layout.custom_tab, null);
        tab_rentals.findViewById(R.id.icon).setBackgroundResource(R.drawable.rent);
        mTabLayout.getTabAt(0).setCustomView(tab_rentals);

        View tab_mail = getLayoutInflater().inflate(R.layout.custom_tab, null);
        tab_mail.findViewById(R.id.icon).setBackgroundResource(R.drawable.mailselector);
        mTabLayout.getTabAt(1).setCustomView(tab_mail);
//        mTabLayout.getTabAt(2).setIcon(R.drawable.map)//TODO

        View tab_social = getLayoutInflater().inflate(R.layout.custom_tab, null);
        tab_social.findViewById(R.id.icon).setBackgroundResource(R.drawable.socialselector);
        mTabLayout.getTabAt(2).setCustomView(tab_social);

        View tab_info = getLayoutInflater().inflate(R.layout.custom_tab, null);
        tab_info.findViewById(R.id.icon).setBackgroundResource(R.drawable.infoselector);
        mTabLayout.getTabAt(3).setCustomView(tab_info);
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

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new RentFrag(), "Category 1");
        adapter.addFragment(new MailFrag(), "Category 2");
//        adapter.addFragment(new MapFrag(), "Category 3");
        adapter.addFragment(new SocialFrag(), "Category 3");
        adapter.addFragment(new InfoFrag(), "Category 4");
        viewPager.setAdapter(adapter);
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
