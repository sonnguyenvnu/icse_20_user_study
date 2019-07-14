public static void getPointLine(List<String> shapes,MapView mMapView,GraphicsOverlay gLayerRoadHistory,GraphicsOverlay gLayerRoadHistoryLine,PictureMarkerSymbol mMarkerSymbolHistory,boolean isGPS){
  gLayerRoadHistory.getGraphics().clear();
  gLayerRoadHistoryLine.getGraphics().clear();
  if (shapes != null) {
    for (    String ldModel : shapes) {
      if (!RxDataTool.isNullString(ldModel)) {
        String shape=ldModel.substring(11,ldModel.length() - 1);
        if (!RxDataTool.isNullString(shape)) {
          String[] arrayShape=shape.split(",");
          PolylineBuilder lineGeometry=new PolylineBuilder(SpatialReference.create(4326));
          for (int i=0; i < arrayShape.length; i++) {
            String str=arrayShape[i];
            if (!RxDataTool.isNullString(str)) {
              String[] arrayPoint=str.split(" ");
              Gps gps=getGps(RxDataTool.stringToDouble(arrayPoint[0]),RxDataTool.stringToDouble(arrayPoint[1]),isGPS);
              if (gps != null) {
                double locx=gps.getLongitude();
                double locy=gps.getLatitude();
                Point mapPoint=new Point(locx,locy);
                if (i == 0) {
                  markLocation(mMapView,RxDataTool.stringToDouble(arrayPoint[0]),RxDataTool.stringToDouble(arrayPoint[1]),gLayerRoadHistory,mMarkerSymbolHistory,isGPS,false);
                  lineGeometry.addPoint(mapPoint);
                }
 else {
                  if (i == arrayShape.length - 1) {
                    lineGeometry.addPoint(mapPoint);
                    SimpleLineSymbol lineSymbol=new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID,0xCC52BA97,3);
                    Graphic lineGraphic=new Graphic(lineGeometry.toGeometry());
                    SimpleRenderer lineRenderer=new SimpleRenderer(lineSymbol);
                    gLayerRoadHistoryLine.setRenderer(lineRenderer);
                    gLayerRoadHistoryLine.getGraphics().add(lineGraphic);
                    markLocation(mMapView,RxDataTool.stringToDouble(arrayPoint[0]),RxDataTool.stringToDouble(arrayPoint[1]),gLayerRoadHistory,mMarkerSymbolHistory,isGPS,false);
                  }
 else {
                    lineGeometry.addPoint(mapPoint);
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
