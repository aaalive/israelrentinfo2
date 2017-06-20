package info.samson.objects;

import com.google.android.gms.maps.model.LatLng;

public class RentalLocation {

	private String mUrl;
	private String mTitle;
	private LatLng mLatLng;

	public RentalLocation(String url, String title, LatLng latLng) {
		setUrl(url);
		setTitle(title);
		setLatLng(latLng);}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String mUrl) {
		this.mUrl = mUrl;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public LatLng getLatLng() {
		return mLatLng;
	}

	public void setLatLng(LatLng mLatLng) {
		this.mLatLng = mLatLng;
	}

}
