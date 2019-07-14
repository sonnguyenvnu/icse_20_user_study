/** 
 * ?? <p>??????? {@link #unRegisterLocation()}</p> <p>?????  {@code <uses-permission android:name="android.permission.INTERNET"/>}</p> <p>?????  {@code <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>}</p> <p>?????  {@code <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>}</p> <p>?? {@code minDistance}?0???? {@code minTime}??????</p> <p> {@code minDistance}??0??? {@code minDistance}???</p> <p>????0???????</p>
 * @param minTime     ???????????????
 * @param minDistance ???????????????????????????????????
 * @param listener    ?????????
 * @return {@code true}: ?????<br> {@code false}: ?????
 */
public static boolean registerLocation(Context context,long minTime,long minDistance,OnLocationChangeListener listener){
  if (listener == null) {
    return false;
  }
  if (ContextCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions((Activity)context,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
    ActivityCompat.requestPermissions((Activity)context,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1);
    return false;
  }
  mLocationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
  mListener=listener;
  if (!isLocationEnabled(context)) {
    RxToast.showToast(context,"????????????",500);
    return false;
  }
  String provider=mLocationManager.getBestProvider(getCriteria(),true);
  Location location=mLocationManager.getLastKnownLocation(provider);
  if (location != null) {
    listener.getLastKnownLocation(location);
  }
  if (myLocationListener == null) {
    myLocationListener=new MyLocationListener();
  }
  mLocationManager.requestLocationUpdates(provider,minTime,minDistance,myLocationListener);
  return true;
}
