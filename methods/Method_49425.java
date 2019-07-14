@SuppressWarnings("unchecked") private static Object convertGeoshape(Geoshape geoshape,Mapping mapping){
  if (geoshape.getType() == Geoshape.Type.POINT && Mapping.PREFIX_TREE != mapping) {
    final Geoshape.Point p=geoshape.getPoint();
    return new double[]{p.getLongitude(),p.getLatitude()};
  }
 else   if (geoshape.getType() == Geoshape.Type.BOX) {
    final Rectangle box=geoshape.getShape().getBoundingBox();
    final Map<String,Object> map=new HashMap<>();
    map.put("type","envelope");
    map.put("coordinates",new double[][]{{box.getMinX(),box.getMaxY()},{box.getMaxX(),box.getMinY()}});
    return map;
  }
 else   if (geoshape.getType() == Geoshape.Type.CIRCLE) {
    try {
      final Map<String,Object> map=geoshape.toMap();
      map.put("radius",map.get("radius") + ((Map<String,String>)map.remove("properties")).get("radius_units"));
      return map;
    }
 catch (    final IOException e) {
      throw new IllegalArgumentException("Invalid geoshape: " + geoshape,e);
    }
  }
 else {
    try {
      return geoshape.toMap();
    }
 catch (    final IOException e) {
      throw new IllegalArgumentException("Invalid geoshape: " + geoshape,e);
    }
  }
}
