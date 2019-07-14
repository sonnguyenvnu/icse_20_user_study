public static void mapScaleIn(MapView mMapView){
  double mScale=mMapView.getMapScale();
  mMapView.setViewpointScaleAsync(mScale * 0.5);
}
