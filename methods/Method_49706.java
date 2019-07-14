@Override public void showSatelliteView(){
  if (mMap != null) {
    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    mSatelliteButton.setSelected(true);
  }
}
