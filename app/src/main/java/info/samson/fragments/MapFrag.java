// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package info.samson.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;


import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import info.samson.R;
import info.samson.adaptres.GridIconAdapter;
import info.samson.objects.ImageType;
import info.samson.objects.RentalLocation;

import java.util.Iterator;
import java.util.List;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFrag extends Fragment {

	MapView mapView;
	GoogleMap map;
	RentalLocation [] locations= {
			new RentalLocation("http://israelrent.info/index/laguna/0-32",  "Laguna", new LatLng(31.681512, 34.559307)),
			new RentalLocation("http://israelrent.info/index/dalilaapartment/0-65#.U4WV9_mSwxM",  "Dalila", new LatLng(31.678660, 34.555952)),
			new RentalLocation("http://israelrent.info/index/gideon/0-41", "Shanty",  new LatLng(31.681813, 34.573553)),
			new RentalLocation("http://israelrent.info/index/siesta/0-56#.U4WDaPmSwxM", "Star", new LatLng(31.679818, 34.557869)),
			new RentalLocation("http://israelrent.info/index/castle_spa/0-21", "Castle", new LatLng(31.686518, 34.572604)),
			new RentalLocation("http://israelrent.info/index/retro_apartment/0-76", "Retro", new LatLng(31.679507, 34.557568)),
			new RentalLocation("http://israelrent.info/index/santaterra/0-67#.U4WWZfmSwxM", "Santa", new LatLng(31.681711, 34.559508)),
			new RentalLocation("http://israelrent.info/index/nicole_apartment_ashkelon/0-80", "Nicole",new LatLng(31.681310, 34.559105)),
			new RentalLocation("http://israelrent.info/index/breeze_apartment/0-74", "Breeze",new LatLng(31.679705, 34.557766)),
			new RentalLocation("http://israelrent.info/index/space_apartment_ashkelon/0-77", "Space",new LatLng(31.678456, 34.556280)),
			new RentalLocation("http://israelrent.info/index/beach/0-23", "Beach",new LatLng(31.681464, 34.559213)),
			new RentalLocation("http://israelrent.info/index/paradise/0-24#.U4V5yPmSwxM", "Paradise",new LatLng(31.680562, 34.557510)),
			new RentalLocation("http://israelrent.info/index/astoria/0-57#.U4WUzPmSwxM", "Astoria",new LatLng(31.680361, 34.557311)),
			new RentalLocation("http://israelrent.info/index/daniela/0-35#.U4V_Z_mSwxM", "Daniela",new LatLng(31.681961, 34.559601)),
			new RentalLocation("http://israelrent.info/index/victory/0-39#.U4V-FPmSwxM", "Victory",new LatLng(31.680361, 34.557311)),
			new RentalLocation("http://israelrent.info/index/sun/0-49#.U4V_tfmSwxM", "Sun",new LatLng(31.674505, 34.558212)),
			new RentalLocation("http://israelrent.info/index/luna/0-48", "Luna",new LatLng(31.679404, 34.557469)),
			new RentalLocation("http://israelrent.info/index/royal/0-47#.U4WBWvmSwxM", "Royal",new LatLng(31.677818, 34.556220)),
			new RentalLocation("http://israelrent.info/index/studio_namal/0-81#.VB3x8fmSwxM", "Studio",new LatLng(31.680161, 34.557109)),
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.map, container, false);
		// Gets the MapView from the XML layout and creates it
		mapView = (MapView) v.findViewById(R.id.mapview);
		mapView.onCreate(savedInstanceState);

		// Gets to GoogleMap from the MapView and does initialization stuff
		mapView.getMapAsync(new OnMapReadyCallback(){

			@Override
			public void onMapReady(GoogleMap googleMap) {
				map =googleMap;
			}
		});
		map.getUiSettings().setMyLocationButtonEnabled(false);
		map.setMyLocationEnabled(true);

		MapsInitializer.initialize(this.getActivity());

		// Updates the location and zoom of the MapView
		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(31.682218, 34.561872), 14);
		map.animateCamera(cameraUpdate);

		for(final RentalLocation rental : locations){
			Marker marker = map.addMarker(
					new MarkerOptions()
					.position(rental.getLatLng())
					.alpha(0.75f)
					.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
					//    .flat(true)
					.title(rental.getTitle())
					);

		}


		map.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(final Marker marker) {

				if (!marker.isInfoWindowShown()){
					marker.showInfoWindow();
				}
				else {
					for(final RentalLocation rental : locations){
						if(marker.getTitle().equals(rental.getTitle())){
							Intent intent = new Intent("android.intent.action.VIEW");
							intent.setData(Uri.parse(rental.getUrl()));
							startActivity(intent);
						}
					}
					marker.hideInfoWindow();
				}


				return true;
			}
		});


		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

			@Override
			public void onInfoWindowClick(Marker marker) {
				for(final RentalLocation rental : locations){
					if(marker.getTitle().equals(rental.getTitle())){
						Intent intent = new Intent("android.intent.action.VIEW");
						intent.setData(Uri.parse(rental.getUrl()));
						startActivity(intent);
					}
				}
			}
		});


		return v;
	}



	@Override
	public void onResume() {
		mapView.onResume();
		super.onResume();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mapView.onLowMemory();
	}

}