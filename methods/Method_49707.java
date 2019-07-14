@Override public void showRoadView(){
  if (mMap != null) {
    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    mSatelliteButton.setSelected(false);
  }
}
