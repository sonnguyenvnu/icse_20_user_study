@Override public void start(OnLocationUpdatedListener listener,LocationParams params,boolean singleUpdate){
  this.listener=listener;
  if (listener == null) {
    logger.d("Listener is null, you sure about this?");
  }
  Criteria criteria=getProvider(params);
  if (singleUpdate) {
    if (ActivityCompat.checkSelfPermission(mContext,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      logger.i("Permission check failed. Please handle it in your app before setting up location");
      return;
    }
    locationManager.requestSingleUpdate(criteria,this,Looper.getMainLooper());
  }
 else {
    locationManager.requestLocationUpdates(params.getInterval(),params.getDistance(),criteria,this,Looper.getMainLooper());
  }
}
