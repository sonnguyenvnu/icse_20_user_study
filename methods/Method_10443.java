public static Point markLocation(MapView mMapView,double longitude,double latitude,GraphicsOverlay gLayerPos,PictureMarkerSymbol mMarkerSymbol,boolean isGPS,boolean isCenter){
  Gps gps=getGps(longitude,latitude,isGPS);
  return markLocation(mMapView,gps,gLayerPos,mMarkerSymbol,isCenter);
}
