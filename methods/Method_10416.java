private static void handleLayerInfo(RxMapLayerInfo layerInfo){
  layerInfo.setOrigin(RxLayerInfoFactory.ORIGIN);
  layerInfo.setScales(RxLayerInfoFactory.SCALES);
  layerInfo.setSrid(RxLayerInfoFactory.SRID);
  layerInfo.setxMin(RxLayerInfoFactory.X_MIN);
  layerInfo.setyMin(RxLayerInfoFactory.Y_MIN);
  layerInfo.setxMax(RxLayerInfoFactory.X_MAX);
  layerInfo.setyMax(RxLayerInfoFactory.Y_MAX);
  layerInfo.setResolutions(RxLayerInfoFactory.RESOLUTIONS);
}
