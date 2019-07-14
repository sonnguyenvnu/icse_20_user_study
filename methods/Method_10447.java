public static void initPointLine(String shapeStr,List<Gps> gpsList,GraphicsOverlay gLayerPos,PictureMarkerSymbol orginLocationSymbol,PictureMarkerSymbol finalLocationSymbol,PictureMarkerSymbol locationSymbol,GraphicsOverlay gLayerPosLine,MapView mMapView){
  gLayerPosLine.getGraphics().clear();
  gLayerPos.getGraphics().clear();
  if (!RxDataTool.isNullString(shapeStr)) {
    String shape=shapeStr;
    if (shapeStr.contains("LINESTRING")) {
      shape=shapeStr.substring(12,shapeStr.length() - 1);
    }
    if (!RxDataTool.isNullString(shape)) {
      String[] arrayShape=shape.split(", ");
      String[] originShape=arrayShape[0].split(" ");
      Gps originGps=getGps(RxDataTool.stringToDouble(originShape[0]),RxDataTool.stringToDouble(originShape[1]),true);
      Point mapOriginPoint=new Point(originGps.getLongitude(),originGps.getLatitude(),SpatialReference.create(4326));
      Graphic graphic=new Graphic(mapOriginPoint,orginLocationSymbol);
      gLayerPos.getGraphics().add(graphic);
      String[] finalShape=arrayShape[arrayShape.length - 1].split(" ");
      Gps finalGps=getGps(RxDataTool.stringToDouble(finalShape[0]),RxDataTool.stringToDouble(finalShape[1]),true);
      Point mapFinalPoint=new Point(finalGps.getLongitude(),finalGps.getLatitude(),SpatialReference.create(4326));
      Graphic graphicFinal=new Graphic(mapFinalPoint,finalLocationSymbol);
      gLayerPos.getGraphics().add(graphicFinal);
      String shapes=arrayShape[arrayShape.length / 2];
      String[] currentPoint=shapes.split(" ");
      Gps currentGps=getGps(RxDataTool.stringToDouble(currentPoint[0]),RxDataTool.stringToDouble(currentPoint[1]),true);
      Point currentMapPoint=new Point(currentGps.getLongitude(),currentGps.getLatitude(),SpatialReference.create(4326));
      mMapView.setViewpointCenterAsync(currentMapPoint,60000);
      PolylineBuilder lineGeometry=new PolylineBuilder(SpatialReference.create(4326));
      for (int i=0; i < arrayShape.length; i++) {
        String str=arrayShape[i];
        if (!RxDataTool.isNullString(str)) {
          String[] arrayPoint=str.split(" ");
          Gps gps=getGps(RxDataTool.stringToDouble(arrayPoint[0]),RxDataTool.stringToDouble(arrayPoint[1]),true);
          if (gps != null) {
            double locx=gps.getLongitude();
            double locy=gps.getLatitude();
            Point mapPoint=new Point(locx,locy);
            if (i == 0) {
              lineGeometry.addPoint(mapPoint);
            }
 else {
              if (i == arrayShape.length - 1) {
                lineGeometry.addPoint(mapPoint);
                SimpleLineSymbol lineSymbol=new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID,0xCC2196F3,5);
                Graphic lineGraphic=new Graphic(lineGeometry.toGeometry());
                SimpleRenderer lineRenderer=new SimpleRenderer(lineSymbol);
                gLayerPosLine.setRenderer(lineRenderer);
                gLayerPosLine.getGraphics().add(lineGraphic);
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
  if (gpsList != null) {
    for (    Gps gps : gpsList) {
      Point mapPoint=new Point(gps.getLongitude(),gps.getLatitude(),SpatialReference.create(4326));
      Graphic graphic=new Graphic(mapPoint,locationSymbol);
      gLayerPos.getGraphics().add(graphic);
    }
  }
}
