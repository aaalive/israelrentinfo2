// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package info.samson.fragments;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class InfoFrag extends Fragment
{

	private ImageType mImages[];
	private ArrayList<Boolean> mInfoStarred;
	SamsonApp mApp;
	
	
	public InfoFrag()
	{
	}

	@Override
	public void onCreate(Bundle bundle)
	{
		mApp = (SamsonApp) getActivity().getApplication();
		mInfoStarred = mApp.getListBoolean(SamsonApp.INFO_STARRED_LIST);
		super.onCreate(bundle);
	}
	
	@Override
	public void onStop(){
		int i=0;
		for (ImageType image:mImages){
			mInfoStarred.add(i, mImages[i].isSelected());
			i++;		
		}
		mApp.putListBoolean(SamsonApp.INFO_STARRED_LIST, mInfoStarred);
		super.onStop();

	}
	
	
	public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
	{
		if (viewgroup == null)
		{
			return null;
		} else
		{
			return layoutinflater.inflate(R.layout.info, viewgroup, false);
		}
	}

	public void onStart()
	{
		super.onStart();
		mInfoStarred = mApp.getListBoolean(SamsonApp.INFO_STARRED_LIST);

		ListView listview = (ListView)getView().findViewById(R.id.rentals_list);
		ImageType aimagetype[] = new ImageType[16];
		aimagetype[0] = new ImageType("http://israelrent.info/news/nashi_kontakty/2013-12-08-36#.U4bovPmSwxM", "http://israelrent.ucoz.de/_nw/0/s92610238.jpg", "\u041F\u043E\u043F\u0443\u043B\u044F\u0440\u043D\u044B\u0435 \u043C\u0435\u0441\u0442\u0430 \u043F\u043E\u043A\u0443\u043F\u043E\u043A \u0438 \u043E\u0442\u0434\u044B\u0445\u0430 \u0432 \u0410\u0448\u043A\u0435\u043B\u043E\u043D\u0435", 0);
		aimagetype[1] = new ImageType("http://israelrent.info/news/kratkosrochnaja_arenda_kvartiry_v_ashkelone_i_poleznye_sovety_turistu_v_izraile/2013-07-12-30#.U4bqfPmSwxM", "http://israelrent.ucoz.de/_nw/0/s50269290.jpg", "\u0410\u0448\u043A\u0435\u043B\u043E\u043D \u0438 \u043F\u043E\u043B\u0435\u0437\u043D\u044B\u0435 \u0441\u043E\u0432\u0435\u0442\u044B \u0442\u0443\u0440\u0438\u0441\u0442\u0443 \u0432 \u0418\u0437\u0440\u0430\u0438\u043B\u0435", 0);
		aimagetype[2] = new ImageType("http://israelrent.info/news/how_to_arrive_in_israel_from_ben_gurion_airport_to_ashkelon/2013-07-09-29#.U4bqwvmSwxM", "http://israelrent.ucoz.de/_nw/0/s54313510.jpg", "\u041A\u0430\u043A \u0434\u043E\u0431\u0440\u0430\u0442\u044C\u0441\u044F \u0438\u0437 \u0430\u044D\u0440\u043E\u043F\u043E\u0440\u0442\u0430 \u0411\u0435\u043D \u0413\u0443\u0440\u0438\u043E\u043D \u0432 \u0410\u0448\u043A\u0435\u043B\u043E\u043D", 0);
		aimagetype[3] = new ImageType("http://israelrent.info/news/prices_of_accommodation_services_and_goods_in_israel/2013-07-02-27#.U4cCsPmSwxM", "http://israelrent.ucoz.de/_nw/0/s49053615.jpg", "\u0426\u0435\u043D\u044B \u043D\u0430 \u0442\u043E\u0432\u0430\u0440\u044B, \u0443\u0441\u043B\u0443\u0433\u0438 \u0438 \u043F\u0440\u043E\u0436\u0438\u0432\u0430\u043D\u0438\u0435 \u0432 \u0418\u0437\u0440\u0430\u0438\u043B\u0435", 0);
		aimagetype[4] = new ImageType("http://israelrent.info/news/puteshestvie_iz_ashkelona_v_pustynju_negev_put_blagovonij/2012-10-29-23#.U4cDMPmSwxM", "http://israelrent.ucoz.de/_nw/0/s13964244.jpg", "\u041F\u0443\u0442\u0435\u0448\u0435\u0441\u0442\u0432\u0438\u0435 \u0438\u0437 \u0410\u0448\u043A\u0435\u043B\u043E\u043D\u0430 \u0432 \u043F\u0443\u0441\u0442\u044B\u043D\u044E \u041D\u0435\u0433\u0435\u0432", 75);
		aimagetype[5] = new ImageType("http://israelrent.info/news/prazdniki_i_vykhodnye_dni_v_izraile/2012-07-28-17", "http://israelrent.ucoz.de/_nw/0/s20954615.jpg", "\u041F\u0440\u0430\u0437\u0434\u043D\u0438\u043A\u0438 \u0438 \u0432\u044B\u0445\u043E\u0434\u043D\u044B\u0435 \u0434\u043D\u0438 \u0432 \u0418\u0437\u0440\u0430\u0438\u043B\u0435", 70);
		aimagetype[6] = new ImageType("http://israelrent.info/news/otdykh_v_khamej_joav_termomineralnye_istochniki_i_spa_v_10_minutakh_ezdy_ot_ashkelona/2012-04-30-14#.U4cGjfmSwxM", "http://israelrent.ucoz.de/_nw/0/s37214822.jpg", "\u041E\u0442\u0434\u044B\u0445 \u0432 \u0425\u0430\u043C\u0435\u0439 \u0419\u043E\u0430\u0432, \u0442\u0435\u0440\u043C\u043E\u043C\u0438\u043D\u0435\u0440\u0430\u043B\u044C\u043D\u044B\u0435 \u0438\u0441\u0442\u043E\u0447\u043D\u0438\u043A\u0438 \u0438 \u0441\u043F\u0430", 75);
		aimagetype[7] = new ImageType("http://israelrent.info/news/desjat_mifov_ob_izraile_realnost_ili_vydumka/2011-10-09-13#.U4cG8PmSwxM", "http://israelrent.info/jer.jpg", "\u0414\u0435\u0441\u044F\u0442\u044C \u043C\u0438\u0444\u043E\u0432 \u043E\u0431 \u0418\u0437\u0440\u0430\u0438\u043B\u0435: \u0440\u0435\u0430\u043B\u044C\u043D\u043E\u0441\u0442\u044C \u0438\u043B\u0438 \u0432\u044B\u0434\u0443\u043C\u043A\u0430?", 65);
		aimagetype[8] = new ImageType("http://israelrent.info/news/istorija_granicy_i_dostizhenija_izrailja/2011-07-28-12", "http://israelrent.info/medical_research_rz.jpg", "\u0418\u0441\u0442\u043E\u0440\u0438\u044F, \u0433\u0440\u0430\u043D\u0438\u0446\u044B \u0438 \u0434\u043E\u0441\u0442\u0438\u0436\u0435\u043D\u0438\u044F \u0418\u0437\u0440\u0430\u0438\u043B\u044F", 75);
		aimagetype[9] = new ImageType("http://israelrent.info/news/luchshie_pljazhi_izrailja_na_sredizemnom_i_krasnom_morjakh/2011-07-16-10", "http://israelrent.info/akazib.jpg", "\u041B\u0443\u0447\u0448\u0438\u0435 \u043F\u043B\u044F\u0436\u0438 \u0418\u0437\u0440\u0430\u0438\u043B\u044F", 75);
		aimagetype[10] = new ImageType("http://israelrent.info/news/gorod_ashkelon_odin_iz_drevnejshikh_gorodov_planety/2011-05-15-8#.U4cL4fmSwxM", "http://israelrent.info/62233.jpg", "\u0418\u0441\u0442\u043E\u0440\u0438\u044F \u0433\u043E\u0440\u043E\u0434\u0430 \u0410\u0448\u043A\u0435\u043B\u043E\u043D", 70);
		aimagetype[11] = new ImageType("http://israelrent.info/news/arenda_apartamentov_ili_gostinichnyj_otdykh/2011-04-19-5#.U4cMyPmSwxM", "http://israelrent.ucoz.de/_si/0/s88612363.jpg", "\u0410\u0440\u0435\u043D\u0434\u0430 \u0430\u043F\u0430\u0440\u0442\u0430\u043C\u0435\u043D\u0442\u043E\u0432 \u0438\u043B\u0438 \u0433\u043E\u0441\u0442\u0438\u043D\u0438\u0447\u043D\u044B\u0439 \u043E\u0442\u0434\u044B\u0445?", 90);
		aimagetype[12] = new ImageType("http://israelrent.info/news/puteshestvuem_po_izrailju_mertvoe_more/2011-03-23-3", "http://israelrent.info/DSC_1419.jpg", "\u041F\u0443\u0442\u0435\u0448\u0435\u0441\u0442\u0432\u0443\u0435\u043C \u043F\u043E \u0418\u0437\u0440\u0430\u0438\u043B\u044E, \u041C\u0435\u0440\u0442\u0432\u043E\u0435 \u043C\u043E\u0440\u0435", 75);
		aimagetype[13] = new ImageType("http://israelrent.info/news/chast_3_puteshestvuem_po_izrailju_ierusalim/2011-06-14-9", "http://israelrent.info/IMG_1005.jpg", "\u041F\u0443\u0442\u0435\u0448\u0435\u0441\u0442\u0432\u0443\u0435\u043C \u043F\u043E \u0418\u0437\u0440\u0430\u0438\u043B\u044E,  \u0418\u0435\u0440\u0443\u0441\u0430\u043B\u0438\u043C.", 65);
		aimagetype[14] = new ImageType("http://israelrent.info/news/akko/2011-07-26-11#.U4cK4fmSwxM", "http://israelrent.info/IMG_7954.jpg", "\u041F\u0443\u0442\u0435\u0448\u0435\u0441\u0442\u0432\u0443\u0435\u043C \u043F\u043E \u0418\u0437\u0440\u0430\u0438\u043B\u044E-\u0433\u043E\u0440\u043E\u0434 \u0410\u043A\u043A\u043E", 90);
		aimagetype[15] = new ImageType("http://israelrent.info/news/vstuplenie_puteshestvuem_po_izrailju_gorod_tel_aviv/2011-03-16-2", "http://israelrent.info/IMG_7251.jpg", "\u041F\u0443\u0442\u0435\u0448\u0435\u0441\u0442\u0432\u0443\u0435\u043C \u043F\u043E \u0418\u0437\u0440\u0430\u0438\u043B\u044E, \u0422\u0435\u043B\u044C-\u0410\u0432\u0438\u0432", 35);
		mImages = aimagetype;
		int i=0;
		for (Boolean isStarred:mInfoStarred){

			if(mImages.length>i){

			mImages[i].setSelected(isStarred);
			i++;
			}
		}

		listview.setAdapter(new ListAdapter(getActivity(), getActivity(), mImages, false));
		listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

			final InfoFrag this$0;

			public void onItemClick(AdapterView adapterview, View view, int i, long l)
			{
				Intent intent = new Intent("android.intent.action.VIEW");
				intent.setData(Uri.parse(mImages[i].getSiteUrl()));
				startActivity(intent);
			}


			{
				this$0 = InfoFrag.this;
				// super();
			}
		});
	}

}
