@Override public void clear(){
  if (mItemizedOverlay != null) {
    mItemizedOverlay.removeAllItems();
  }
  if (mPolylines != null) {
    for (    final PathOverlay polyline : mPolylines.values()) {
      mMapView.getOverlays().remove(mPolylines.remove(polyline));
    }
    mPolylines.clear();
  }
}
