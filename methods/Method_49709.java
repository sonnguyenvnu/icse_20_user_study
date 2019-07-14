@Override public void setCameraPosition(double latitude,double longitude,float zoom){
  if (mMap == null) {
    Log.w(TAG,"Couldn't set camera position because the map is null");
  }
 else {
    mMap.moveCamera(CameraUpdateFactory.zoomTo(zoom));
    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude,longitude)));
  }
}
