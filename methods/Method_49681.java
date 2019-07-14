private void determineIfUsingGms(){
  int statusCode=mGoogleApiAvailability.isGooglePlayServicesAvailable(mContext);
  if (statusCode == ConnectionResult.SUCCESS || statusCode == ConnectionResult.SERVICE_UPDATING) {
    mUsingGms=true;
  }
}
