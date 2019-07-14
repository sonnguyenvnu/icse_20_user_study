public static Point markLocationSingle(MapView mMapView,double longitude,double latitude,GraphicsOverlay gLayerPos,PictureMarkerSymbol mMarkerSymbol,boolean isGPS,boolean isCenter){
  gLayerPos.getGraphics().clear();
  return markLocation(mMapView,longitude,latitude,gLayerPos,mMarkerSymbol,isGPS,isCenter);
}
