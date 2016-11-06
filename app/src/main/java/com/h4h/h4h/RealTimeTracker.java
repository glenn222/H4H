package com.h4h.h4h;

import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;

/**
 * Created by _ on 11/6/2016.
 */
public class RealTimeTracker extends MapTracker{

    private LocationSettingsRequest.Builder builder;
    private GoogleApiClient client;

    public RealTimeTracker()
    {
        builder = buildLocationRequest(createLocationRequest());
        buildGoogleApiClient();
    }

    protected synchronized void buildGoogleApiClient() {
        Toast.makeText(this,"buildGoogleApiClient",Toast.LENGTH_SHORT).show();
        client = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();
    }

    protected void allowCurrentLocation(){
        builder = buildLocationRequest(createLocationRequest());

        GoogleApiClient mGoogleApiClient  = new GoogleApiClient.Builder(this).addApi(LocationServices.API).build();

        LocationSettingsResult result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build()).await();
        onResult(result);
    }

    protected LocationRequest createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(20000);
        mLocationRequest.setFastestInterval(15000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        return mLocationRequest;
    }

    protected LocationSettingsRequest.Builder buildLocationRequest(LocationRequest mLocationRequest) {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);

        return builder;
    }

}
