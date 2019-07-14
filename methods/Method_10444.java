public static Point markLocation(MapView mMapView,Gps gps,GraphicsOverlay gLayerPos,PictureMarkerSymbol mMarkerSymbol,boolean isCenter){
  double locx=gps.getLongitude();
  double locy=gps.getLatitude();
  Point mapPoint=new Point(locx,locy,SpatialReference.create(4326));
  Graphic graphic=new Graphic(mapPoint,mMarkerSymbol);
  gLayerPos.getGraphics().add(graphic);
  if (isCenter) {
    mMapView.setViewpointCenterAsync(mapPoint,600);
  }
  return mapPoint;
}
