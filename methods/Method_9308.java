private void onMapInit(){
  if (googleMap == null) {
    return;
  }
  if (messageObject != null) {
    if (messageObject.isLiveLocation()) {
      LiveLocation liveLocation=addUserMarker(messageObject.messageOwner);
      if (!getRecentLocations()) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(liveLocation.marker.getPosition(),googleMap.getMaxZoomLevel() - 4));
      }
    }
 else {
      LatLng latLng=new LatLng(userLocation.getLatitude(),userLocation.getLongitude());
      try {
        googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin)));
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      CameraUpdate position=CameraUpdateFactory.newLatLngZoom(latLng,googleMap.getMaxZoomLevel() - 4);
      googleMap.moveCamera(position);
      firstFocus=false;
      getRecentLocations();
    }
  }
 else {
    userLocation=new Location("network");
    userLocation.setLatitude(20.659322);
    userLocation.setLongitude(-11.406250);
  }
  try {
    googleMap.setMyLocationEnabled(true);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  googleMap.getUiSettings().setMyLocationButtonEnabled(false);
  googleMap.getUiSettings().setZoomControlsEnabled(false);
  googleMap.getUiSettings().setCompassEnabled(false);
  googleMap.setOnMyLocationChangeListener(location -> {
    positionMarker(location);
    LocationController.getInstance(currentAccount).setGoogleMapLocation(location,isFirstLocation);
    isFirstLocation=false;
  }
);
  positionMarker(myLocation=getLastLocation());
  if (checkGpsEnabled && getParentActivity() != null) {
    checkGpsEnabled=false;
    if (!getParentActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)) {
      return;
    }
    try {
      LocationManager lm=(LocationManager)ApplicationLoader.applicationContext.getSystemService(Context.LOCATION_SERVICE);
      if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
        builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
        builder.setMessage(LocaleController.getString("GpsDisabledAlert",R.string.GpsDisabledAlert));
        builder.setPositiveButton(LocaleController.getString("ConnectingToProxyEnable",R.string.ConnectingToProxyEnable),(dialog,id) -> {
          if (getParentActivity() == null) {
            return;
          }
          try {
            getParentActivity().startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
          }
 catch (          Exception ignore) {
          }
        }
);
        builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
        showDialog(builder.create());
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
}
