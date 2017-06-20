// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package info.samson.adaptres;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import info.samson.R;
import info.samson.helpers.Helper;

public class GridIconAdapter extends BaseAdapter
{

	private Context mContext;
	public Integer [] mThumbIds  = {
		R.drawable.p,
		R.drawable.w,
		R.drawable.s,
		R.drawable.yt,
		R.drawable.g,
		R.drawable.t,
		R.drawable.f,
		R.drawable.o,
		R.drawable.v,
		R.drawable.lj};

	public GridIconAdapter(Context context)
	{

		mContext = context;
	}

	public int getCount()
	{
		return mThumbIds.length;
	}

	public Object getItem(int i)
	{
		return mThumbIds[i];
	}

	public long getItemId(int i)
	{
		return 0L;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		ImageView imageview;
		if (view == null)
		{
			imageview = new ImageView(mContext);
			int j = Helper.getScreenWidthResolution(mContext) / 5;
			imageview.setLayoutParams(new android.widget.AbsListView.LayoutParams(j, j));
			imageview.setScaleType(android.widget.ImageView.ScaleType.CENTER_CROP);
			imageview.setPadding(5, 5, 5, 5);
		} else
		{
			imageview = (ImageView)view;
		}
		imageview.setImageResource(mThumbIds[i]);
		return imageview;
	}
}
