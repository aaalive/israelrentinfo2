// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package info.samson.helpers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ResizeableImageView extends ImageView
{

    public ResizeableImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected void onMeasure(int i, int j)
    {
        Drawable drawable = getDrawable();
        if (drawable != null)
        {
            int k = android.view.View.MeasureSpec.getSize(i);
            setMeasuredDimension(k, (int)Math.ceil(((float)k * (float)drawable.getIntrinsicHeight()) / (float)drawable.getIntrinsicWidth()));
            return;
        } else
        {
            super.onMeasure(i, j);
            return;
        }
    }
}
