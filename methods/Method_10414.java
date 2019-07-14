public static RxMapLayerInfo getLayerInfo(int layerType){
  RxMapLayerInfo layerInfo=new RxMapLayerInfo();
  layerInfo.setLayerType(layerType);
  handleLayerInfo(layerInfo);
switch (layerType) {
case RxMapLayerTypes.AMAP_VECTOR:
    layerInfo.setLayerName(RxLayerInfoFactory.AMAP_VECTOR_NAME);
  layerInfo.setCachePathName("AMAP_VECTOR");
break;
case RxMapLayerTypes.AMAP_IMAGE:
layerInfo.setLayerName(RxLayerInfoFactory.AMAP_IMAGE_NAME);
layerInfo.setCachePathName("AMAP_IMAGE");
break;
case RxMapLayerTypes.AMAP_ROAD:
layerInfo.setLayerName(RxLayerInfoFactory.AMAP_ROAD_NAME);
layerInfo.setCachePathName("AMAP_ROAD");
break;
case RxMapLayerTypes.AMAP_TRAFFIC:
layerInfo.setCachePathName("AMAP_TRAFFIC");
break;
case RxMapLayerTypes.BAIDU_MAP_VECTOR:
layerInfo.setLayerName(RxLayerInfoFactory.BAIDU_MAP_VECTOR_NAME);
layerInfo.setCachePathName("BAIDU_MAP_VECTOR");
layerInfo.setMinZoomLevel(3);
layerInfo.setMaxZoomLevel(19);
break;
case RxMapLayerTypes.BAIDU_MAP_IMAGE:
layerInfo.setCachePathName("BAIDU_MAP_IMAGE");
layerInfo.setMinZoomLevel(3);
layerInfo.setMaxZoomLevel(19);
break;
case RxMapLayerTypes.BAIDU_MAP_ROAD:
layerInfo.setLayerName(RxLayerInfoFactory.BAIDU_MAP_ROAD_NAME);
layerInfo.setCachePathName("BAIDU_MAP_ROAD");
layerInfo.setMinZoomLevel(3);
layerInfo.setMaxZoomLevel(19);
break;
case RxMapLayerTypes.BAIDU_MAP_TRAFFIC:
layerInfo.setCachePathName("BAIDU_MAP_TRAFFIC");
layerInfo.setMinZoomLevel(3);
layerInfo.setMaxZoomLevel(19);
break;
case RxMapLayerTypes.TENCENT_MAP_VECTOR:
layerInfo.setLayerName(RxLayerInfoFactory.TENCENT_MAP_VECTOR_NAME);
layerInfo.setCachePathName("TENCENT_MAP_VECTOR");
break;
case RxMapLayerTypes.TENCENT_MAP_VECTOR_NIGHT:
layerInfo.setLayerName(RxLayerInfoFactory.TENCENT_MAP_VECTOR_NIGHT_NAME);
layerInfo.setCachePathName("TENCENT_MAP_VECTOR_NIGHT");
break;
case RxMapLayerTypes.TENCENT_MAP_IMAGE:
layerInfo.setLayerName(RxLayerInfoFactory.TENCENT_MAP_IMAGE_NAME);
layerInfo.setCachePathName("TENCENT_MAP_IMAGE");
break;
case RxMapLayerTypes.TENCENT_MAP_TERRAIN:
layerInfo.setLayerName(RxLayerInfoFactory.TENCENT_MAP_TERRAIN_NAME);
layerInfo.setCachePathName("TENCENT_MAP_TERRAIN");
break;
case RxMapLayerTypes.TENCENT_MAP_ROAD:
layerInfo.setLayerName(RxLayerInfoFactory.TENCENT_MAP_ROAD_NAME);
layerInfo.setCachePathName("TENCENT_MAP_ROAD");
break;
case RxMapLayerTypes.GOOGLE_MAP_VECTOR:
layerInfo.setLayerName(RxLayerInfoFactory.GOOGLE_MAP_VECTOR_NAME);
layerInfo.setCachePathName("GOOGLE_MAP_VECTOR");
layerInfo.setMaxZoomLevel(21);
break;
case RxMapLayerTypes.GOOGLE_MAP_IMAGE:
layerInfo.setLayerName(RxLayerInfoFactory.GOOGLE_MAP_IMAGE_IMAGE);
layerInfo.setCachePathName("GOOGLE_MAP_IMAGE");
layerInfo.setMaxZoomLevel(21);
break;
case RxMapLayerTypes.GOOGLE_MAP_TERRAIN:
layerInfo.setLayerName(RxLayerInfoFactory.GOOGLE_MAP_TERRAIN_NAME);
layerInfo.setCachePathName("GOOGLE_MAP_TERRAIN");
layerInfo.setMaxZoomLevel(21);
break;
default :
break;
}
return layerInfo;
}
