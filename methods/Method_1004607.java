private void invalidate(){
  if (detached) {
    return;
  }
  mMapView.postInvalidate();
}
