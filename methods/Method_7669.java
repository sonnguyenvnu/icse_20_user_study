private void prepareForDrawerOpen(MotionEvent ev){
  maybeStartTracking=false;
  startedTracking=true;
  if (ev != null) {
    startedTrackingX=(int)ev.getX();
  }
  beginTrackingSent=false;
}
