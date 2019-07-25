public void clear(){
  CommonLogger.e("??????");
  if (mLocationClient != null) {
    mLocationClient.onDestroy();
    mLocationClient=null;
  }
  longitude=0;
  latitude=0;
}
