public static String getLayerUrl(RxMapLayerInfo layerInfo,int level,int col,int row){
  String layerUrl=null;
switch (layerInfo.getLayerType()) {
case RxMapLayerTypes.AMAP_VECTOR:
case RxMapLayerTypes.AMAP_IMAGE:
case RxMapLayerTypes.AMAP_ROAD:
    layerUrl="http://webst0" + ((col + row) % 4 + 1) + ".is.autonavi.com/appmaptile?style=" + layerInfo.getLayerName() + "&x=" + col + "&y=" + row + "&z=" + level;
  break;
case RxMapLayerTypes.AMAP_TRAFFIC:
Calendar calendar=Calendar.getInstance();
calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
int day=calendar.get(Calendar.DAY_OF_WEEK);
int hh=calendar.get(Calendar.HOUR_OF_DAY);
int mm=calendar.get(Calendar.MINUTE);
layerUrl="http://history.traffic.amap.com/traffic?type=2" + "&day=" + day + "&hh=" + hh + "&mm=" + mm + "&x=" + col + "&y=" + row + "&z=" + level;
break;
case RxMapLayerTypes.BAIDU_MAP_VECTOR:
case RxMapLayerTypes.BAIDU_MAP_ROAD:
int offsetV=(int)(Math.pow(2,level - 1));
layerUrl="http://online" + ((col + row) % 8 + 1) + ".map.bdimg.com/onlinelabel/?qt=tile" + "&x=" + (col - offsetV) + "&y=" + (offsetV - row - 1) + "&z=" + level + "&styles=" + layerInfo.getLayerName();
break;
case RxMapLayerTypes.BAIDU_MAP_IMAGE:
int offsetI=(int)(Math.pow(2,level - 1));
layerUrl="http://shangetu" + ((col + row) % 8 + 1) + ".map.bdimg.com/it/u=" + "x=" + (col - offsetI) + ";y=" + (offsetI - row - 1) + ";z=" + level + ";v=009;type=sate&fm=46";
break;
case RxMapLayerTypes.BAIDU_MAP_TRAFFIC:
int offsetT=(int)(Math.pow(2,level - 1));
layerUrl="http://its.map.baidu.com:8002/traffic/TrafficTileService?" + "level=" + level + "&x=" + (col - offsetT) + "&y=" + (offsetT - row - 1) + "&time=" + System.currentTimeMillis();
break;
case RxMapLayerTypes.TENCENT_MAP_VECTOR:
case RxMapLayerTypes.TENCENT_MAP_VECTOR_NIGHT:
case RxMapLayerTypes.TENCENT_MAP_ROAD:
row=(int)Math.pow(2,level) - 1 - row;
layerUrl="http://rt" + (col % 4) + ".map.gtimg.com/tile?" + "z=" + level + "&x=" + col + "&y=" + row + "&type=vector&styleid=" + layerInfo.getLayerName();
break;
case RxMapLayerTypes.TENCENT_MAP_IMAGE:
case RxMapLayerTypes.TENCENT_MAP_TERRAIN:
row=(int)Math.pow(2,level) - 1 - row;
layerUrl="http://p" + (col % 4) + ".map.gtimg.com/" + layerInfo.getLayerName() + "/" + level + "/" + (int)Math.floor(col / 16) + "/" + (int)Math.floor(row / 16) + "/" + col + "_" + row + ".jpg";
break;
case RxMapLayerTypes.GOOGLE_MAP_VECTOR:
case RxMapLayerTypes.GOOGLE_MAP_IMAGE:
case RxMapLayerTypes.GOOGLE_MAP_TERRAIN:
row=(int)Math.pow(2,level) - 1 - row;
layerUrl="http://mt" + (col % 4) + ".google.cn/vt/lyrs=" + layerInfo.getLayerName() + "&hl=zh-CN&gl=cn&" + "x=" + col + "&" + "y=" + row + "&" + "z=" + level;
break;
default :
break;
}
return layerUrl;
}
