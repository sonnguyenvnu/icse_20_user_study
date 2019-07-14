private void updateSunTime(Location lastKnownLocation,boolean forceUpdate){
  LocationManager locationManager=(LocationManager)ApplicationLoader.applicationContext.getSystemService(Context.LOCATION_SERVICE);
  if (Build.VERSION.SDK_INT >= 23) {
    Activity activity=getParentActivity();
    if (activity != null) {
      if (activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        activity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},2);
        return;
      }
    }
  }
  if (getParentActivity() != null) {
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
        return;
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  try {
    lastKnownLocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    if (lastKnownLocation == null) {
      lastKnownLocation=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    }
    if (lastKnownLocation == null) {
      lastKnownLocation=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  if (lastKnownLocation == null || forceUpdate) {
    startLocationUpdate();
    if (lastKnownLocation == null) {
      return;
    }
  }
  Theme.autoNightLocationLatitude=lastKnownLocation.getLatitude();
  Theme.autoNightLocationLongitude=lastKnownLocation.getLongitude();
  int[] time=SunDate.calculateSunriseSunset(Theme.autoNightLocationLatitude,Theme.autoNightLocationLongitude);
  Theme.autoNightSunriseTime=time[0];
  Theme.autoNightSunsetTime=time[1];
  Theme.autoNightCityName=null;
  Calendar calendar=Calendar.getInstance();
  calendar.setTimeInMillis(System.currentTimeMillis());
  Theme.autoNightLastSunCheckDay=calendar.get(Calendar.DAY_OF_MONTH);
  Utilities.globalQueue.postRunnable(() -> {
    String name;
    try {
      Geocoder gcd=new Geocoder(ApplicationLoader.applicationContext,Locale.getDefault());
      List<Address> addresses=gcd.getFromLocation(Theme.autoNightLocationLatitude,Theme.autoNightLocationLongitude,1);
      if (addresses.size() > 0) {
        name=addresses.get(0).getLocality();
      }
 else {
        name=null;
      }
    }
 catch (    Exception ignore) {
      name=null;
    }
    final String nameFinal=name;
    AndroidUtilities.runOnUIThread(() -> {
      Theme.autoNightCityName=nameFinal;
      if (Theme.autoNightCityName == null) {
        Theme.autoNightCityName=String.format("(%.06f, %.06f)",Theme.autoNightLocationLatitude,Theme.autoNightLocationLongitude);
      }
      Theme.saveAutoNightThemeConfig();
      if (listView != null) {
        RecyclerListView.Holder holder=(RecyclerListView.Holder)listView.findViewHolderForAdapterPosition(scheduleUpdateLocationRow);
        if (holder != null && holder.itemView instanceof TextSettingsCell) {
          ((TextSettingsCell)holder.itemView).setTextAndValue(LocaleController.getString("AutoNightUpdateLocation",R.string.AutoNightUpdateLocation),Theme.autoNightCityName,false);
        }
      }
    }
);
  }
);
  RecyclerListView.Holder holder=(RecyclerListView.Holder)listView.findViewHolderForAdapterPosition(scheduleLocationInfoRow);
  if (holder != null && holder.itemView instanceof TextInfoPrivacyCell) {
    ((TextInfoPrivacyCell)holder.itemView).setText(getLocationSunString());
  }
  if (Theme.autoNightScheduleByLocation && Theme.selectedAutoNightType == Theme.AUTO_NIGHT_TYPE_SCHEDULED) {
    Theme.checkAutoNightThemeConditions();
  }
}
