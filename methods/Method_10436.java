public static void mapScaleOut(MapView mMapView){
  double mScale1=mMapView.getMapScale();
  mMapView.setViewpointScaleAsync(mScale1 * 2);
}
