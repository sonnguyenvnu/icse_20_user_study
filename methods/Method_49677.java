public void disconnect(){
  if (isUsingGms() && mGoogleApiClient.isConnected()) {
    mFusedLocationProviderApi.removeLocationUpdates(mGoogleApiClient,this);
    mGoogleApiClient.disconnect();
  }
 else   if (!isUsingGms()) {
    disconnectUsingOldApi();
  }
  mSensorManager.unregisterListener(this);
}
