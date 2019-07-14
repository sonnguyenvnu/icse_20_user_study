@Override public void moveMapToLocation(final OpenLocationCode code){
  if (mMap != null) {
    CodeArea area=code.decode();
    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(area.getCenterLatitude(),area.getCenterLongitude()),INITIAL_MAP_ZOOM));
    drawCodeArea(code);
  }
 else {
    Handler h=new Handler();
    Runnable r=new Runnable(){
      @Override public void run(){
        moveMapToLocation(code);
      }
    }
;
    if (mRetryShowingCurrentLocationOnMap) {
      h.postDelayed(r,2000);
      mRetryShowingCurrentLocationOnMap=false;
    }
  }
}
