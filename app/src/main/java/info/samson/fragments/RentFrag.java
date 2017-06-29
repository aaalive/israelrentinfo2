// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package info.samson.fragments;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import info.samson.R;
import info.samson.activities.SamsonApp;
import info.samson.adaptres.ListAdapter;
import info.samson.objects.ImageType;

public class RentFrag extends Fragment
{

	private ImageType mImages[];
	private SamsonApp app;
	private SamsonApp mApp;
	private ArrayList<Boolean> mRentStarred;
	private Parcelable mState;
	private ListView mListview;

	public RentFrag()
	{
	}

	public void onCreate(Bundle bundle)
	{
		mApp = (SamsonApp) getActivity().getApplication();

		mRentStarred = mApp.getListBoolean(SamsonApp.RENT_STARRED_LIST);
		super.onCreate(bundle);
	}


	@Override
	public void onStop(){
		int i=0;
		for (ImageType image:mImages){
			mRentStarred.add(i, mImages[i].isSelected());
			i++;		
		}
		mApp.putListBoolean(SamsonApp.RENT_STARRED_LIST, mRentStarred);
		mState = mListview.onSaveInstanceState(); //line 1
		super.onStop();

	}



	public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
	{
		if (viewgroup == null)
		{
			return null;
		} else
		{
			return layoutinflater.inflate(R.layout.rent, viewgroup, false);
		}
	}

	public void onStart()
	{
		super.onStart();
		mListview = (ListView)getView().findViewById(R.id.rentals_list);

		final ImageType [] mImagesA= {
				new ImageType("http://israelrent.info/index/laguna/0-32", "http://israelrent.ucoz.de/_si/0/84297969.jpg", "\"Laguna\" 2 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 65, " Ashkelon, 33 Yefe Nof, Israel"),
				new ImageType("http://israelrent.info/index/paradise/0-24#.U4V5yPmSwxM", "http://israelrent.info/Apartments/sky_salon_tv.jpg", "\"Sky\" 3 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 75, "Ashkelon, 33 Yefe Nof, Israel"),
				new ImageType("http://israelrent.info/index/dalilaapartment/0-65#.U4WV9_mSwxM", "http://israelrent.ucoz.de/_si/0/80041597.jpg", "\"Dalila\" 3 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 100, "4 HaShunit, Ashkelon"),
				new ImageType("http://israelrent.info/index/gideon/0-41", "http://israelrent.ucoz.de/_si/0/94083379.jpg", "\"Shanty\" номер", 35, "Sderot Montefiore 2, Ashkelon"),
				new ImageType("http://israelrent.info/index/siesta/0-56#.U4WDaPmSwxM", "http://israelrent.ucoz.de/_si/0/s40574397.jpg", "\"Star\", 2 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 65, "23 Yefe Nof,Ashkelon"),
				new ImageType("http://israelrent.info/index/castle_spa/0-21", "http://israelrent.ucoz.de/_si/0/s19530748.jpg", "\"Castle\" номер", 50, "Yiftach HaGiladi,Ashkelon"),
				new ImageType("http://israelrent.info/index/retro_apartment/0-76", "http://israelrent.info/DSC_1176.jpg", "\"Retro\", 2 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 65, "23 Yefe Nof,Ashkelon"),
				new ImageType("http://israelrent.info/index/santaterra/0-67#.U4WWZfmSwxM", "http://israelrent.ucoz.de/_si/0/s49653967.jpg", "\"Santa\", 2 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 65, "33 Yefe Nof,Ashkelon"),
				new ImageType("http://israelrent.info/index/nicole_apartment_ashkelon/0-80", "http://israelrent.ucoz.de/_si/0/s09784449.jpg", "\"Nicole\" 2 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 65, "33 Yefe Nof,Ashkelon"),
				new ImageType("http://israelrent.info/index/breeze_apartment/0-74", "http://israelrent.ucoz.de/_si/0/s90324280.jpg", "\"Breeze\" 2 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 70, "23 Yefe Nof,Ashkelon"),
				new ImageType("http://israelrent.info/index/space_apartment_ashkelon/0-77", "http://israelrent.ucoz.de/_si/0/s00273222.jpg", "\"Space\" 3.5 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 90, "2 HaShunit, Ashkelon"),
				new ImageType("http://israelrent.info/index/beach/0-23", "http://israelrent.ucoz.de/_si/0/99627892.jpg", "\"Beach\" 2 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 75, "31 Yefe Nof,Ashkelon"),
				new ImageType("http://israelrent.info/index/astoria/0-57#.U4WUzPmSwxM", "http://israelrent.info/Apartments/IMG_20150104_112201.jpg", "\"Alisa\" 3 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 80, "Ashkelon, 33 Yefe Nof, Israel"),
				new ImageType("http://israelrent.info/index/daniela/0-35#.U4V_Z_mSwxM", "http://israelrent.ucoz.de/_si/0/s09217665.jpg", "\"Daniela\" 2 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 75,"35 Yefe Nof,Ashkelon"),
				new ImageType("http://israelrent.info/index/victory/0-39#.U4V-FPmSwxM", "http://israelrent.ucoz.de/_si/0/s96675571.jpg", "\"Victory\" 2 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 75,"37 Yefe Nof,Ashkelon"),
				new ImageType("http://israelrent.info/index/sun/0-49#.U4V_tfmSwxM", "http://israelrent.ucoz.de/_si/0/s14276316.jpg", "\"Sun\" 3 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 75,"91 HaTayasim,Ashkelon"),
				new ImageType("http://israelrent.info/index/luna/0-48", "http://israelrent.ucoz.de/_si/0/s46163206.jpg", "\"Luna\" 2 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 70,"23 Yefe Nof,Ashkelon"),
				new ImageType("http://israelrent.info/index/royal/0-47#.U4WBWvmSwxM", "http://israelrent.ucoz.de/_si/0/s21850224.jpg", "\"Royal\" 2 \u043A\u043E\u043C\u043D\u0430\u0442\u044B", 75,"3 Yefe Nof,Ashkelon"),
				new ImageType("http://israelrent.info/index/studio_namal/0-81#.VB3x8fmSwxM", "http://israelrent.ucoz.de/_si/0/s69165206.jpg", "\"Студио\" 1 комната", 75,"5 HaNamal,Ashkelon")

		};
		mImages= mImagesA;
		int i=0;
		for (Boolean isStarred:mRentStarred){
			if(mImages.length>i){
				mImages[i].setSelected(isStarred);
				i++;
			}
		}

		//set adapter to listview
		mListview.setAdapter(new ListAdapter(getActivity(), getActivity(), mImages, true));
		mListview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

			final RentFrag mRentFrag;

			public void onItemClick(AdapterView adapterview, View view, int i, long l)
			{
				Intent intent = new Intent("android.intent.action.VIEW");
				intent.setData(Uri.parse(mImages[i].getSiteUrl()));
				startActivity(intent);
			}


			{
                mRentFrag = RentFrag.this;
			}
		});			//Restore previous state

		if (mState != null) {
			mListview.onRestoreInstanceState(mState);
		}

	}

}
