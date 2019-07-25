@Override public void destroy(){
  stopLocationProvider();
  mLocation=null;
  mLocationManager=null;
  mMyLocationConsumer=null;
  mIgnorer=null;
}
