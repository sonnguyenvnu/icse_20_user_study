@Override public void clear(){
  if (mItemizedOverlay != null) {
    mItemizedOverlay.removeAllItems();
  }
  if (mPolylines != null) {
    for (    final GooglePolylineOverlay polyline : mPolylines.values()) {
      mMapView.getOverlays().remove(mPolylines.remove(polyline));
    }
    mPolylines.clear();
  }
}
