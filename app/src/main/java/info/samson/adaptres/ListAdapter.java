// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package info.samson.adaptres;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import info.samson.R;
import info.samson.helpers.ImageLoader;
import info.samson.helpers.Utils;
import info.samson.objects.ImageType;

public class ListAdapter extends BaseAdapter
{
	public static class ViewHolder
	{

		public ImageView image;
		public TextView price;
		public ImageView shareBtn;
		public ImageView googleMapsBtn;
		public TextView title;

		
	}


	private Activity activity;
	private ImageLoader imageLoader;
	private LayoutInflater inflater;
	private Context mContext;
	private ImageType mImages[];
	private boolean mIsRentals;

	public ListAdapter(Activity activity1, Context context, ImageType [] images, boolean flag)
	{
		activity = activity1;
		mContext = context;
		mImages = images;
		mIsRentals = flag;
		imageLoader = new ImageLoader(activity1.getApplicationContext());
		
	}

	public int getCount()
	{
		return mImages.length;
	}

	public Object getItem(int i)
	{
		return mImages[i];
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public View getView(final int position, View view, ViewGroup viewgroup)
	{
		 final ViewHolder holder;
		ImageView imageview;

		if (view == null)
		{
			view = ((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.title = (TextView)view.findViewById(R.id.title);
			holder.price = (TextView)view.findViewById(R.id.price);
			holder.image = (ImageView)view.findViewById(R.id.image);
			holder.shareBtn = (ImageView)view.findViewById(R.id.imageBtn);
			holder.googleMapsBtn =  (ImageView)view.findViewById(R.id.googleMapsBtn);

			view.setTag(holder);
		} else
		{
			holder = (ViewHolder)view.getTag();
	        view.setTag (holder);

		}

		

		if(mIsRentals){
			holder.price.setVisibility(View.VISIBLE);
			holder.googleMapsBtn.setVisibility(View.VISIBLE);
			holder.googleMapsBtn.setOnClickListener(new OnClickListener() {

				public void onClick(View view1)
				{
					try
					{

						Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
								Uri.parse("google.navigation:q="+Uri.parse(mImages[position].getAdress())));
						mContext.startActivity(intent);
					}
					catch ( ActivityNotFoundException ex  )
					{
						Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=com.waze" ) );
						mContext.startActivity(intent);
					}
				}
			});
		}
		else{
			holder.price.setVisibility(View.GONE);
			holder.googleMapsBtn.setVisibility(View.GONE);

//			FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(100, 100);
//			lp.setMargins(0, 8,8, 0);
//			lp.gravity=Gravity.RIGHT;
//
//			holder.shareBtn.setLayoutParams(lp);
		}

		
		holder.title.setText(mImages[position].getTitle());
		if (mIsRentals)
		{
			holder.price.setText((new StringBuilder("\u043E\u0442 ")).append(mImages[position].getPrice()).append("$").toString());
		}
		imageview = holder.image;
		imageLoader.DisplayImage(mImages[position].getUrl(), imageview);
		
		if(mImages[position].isSelected()){
			holder.shareBtn.setSelected(true);
		}
		else{
			holder.shareBtn.setSelected(false);

		}
		
		holder.shareBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View view1)
			{
				mImages[position].setSelected(true);
				holder.shareBtn.setSelected(true);
				Intent intent = new Intent("android.intent.action.SEND");
				intent.setType("text/plain");
				intent.putExtra("android.intent.extra.TEXT", mImages[position].getSiteUrl());
				intent.putExtra("android.intent.extra.SUBJECT", mImages[position].getTitle());
				mContext.startActivity(Intent.createChooser(intent, "\u041F\u043E\u0434\u0435\u043B\u0438\u0442\u044C\u0441\u044F"));
				
			}
		});
		return view;
	}

	
	
}
