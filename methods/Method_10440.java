/** 
 * ???? ????
 * @param mMapView      ????
 * @param location      ????
 * @param gLayerPos     ??
 * @param mMarkerSymbol ????
 * @param isGPS         ???GPS???
 * @param isCenter      ?????????????????
 * @return
 */
public static Point markLocationSingle(MapView mMapView,Location location,GraphicsOverlay gLayerPos,PictureMarkerSymbol mMarkerSymbol,boolean isGPS,boolean isCenter){
  gLayerPos.getGraphics().clear();
  return markLocation(mMapView,location,gLayerPos,mMarkerSymbol,isGPS,isCenter);
}
