public static Point markLocation(MapView mMapView,Location location,GraphicsOverlay gLayerPos,PictureMarkerSymbol mMarkerSymbol,boolean isGPS,boolean isCenter){
  Gps gps=getGps(location.getLongitude(),location.getLatitude(),isGPS);
  return markLocation(mMapView,gps,gLayerPos,mMarkerSymbol,isCenter);
}
