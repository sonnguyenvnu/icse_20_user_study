private void disconnectUsingOldApi(){
  mLocationManager.removeUpdates(mGpsLocationListener);
  mLocationManager.removeUpdates(mNetworkLocationListener);
}
