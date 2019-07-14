public void loadCurrentLocation(){
  Toast.makeText(mContext,mContext.getResources().getString(R.string.current_location_loading),Toast.LENGTH_LONG).show();
  mLocationProvider.connect();
}
