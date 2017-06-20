// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package info.samson.objects;


public class ImageType
{

    private int price;
    private boolean selected=false;
    private String siteUrl;
    private String title;
    private String url;
    private String adress;
    private boolean isStarred;

    public ImageType(String s, String s1, String s2, int i)
    {
        siteUrl = s;
        url = s1;
        title = s2;
        price = i;
    }
    
    public ImageType(String s, String s1, String s2, int i, String a)
    {
        siteUrl = s;
        url = s1;
        title = s2;
        price = i;
        adress = a;
    }

    public int getPrice()
    {
        return price;
    }

    public String getSiteUrl()
    {
        return siteUrl;
    }

    public String getTitle()
    {
        return title;
    }

    public String getUrl()
    {
        return url;
    }

    public boolean isSelected()
    
    {
        return selected;
    }

    public void setPrice(int i)
    {
        price = i;
    }

    public void setSelected(boolean flag)
    {
        selected = flag;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setUrl(String s)
    {
        url = s;
    }

	public String getAdress() {
		return adress;
	}
}
