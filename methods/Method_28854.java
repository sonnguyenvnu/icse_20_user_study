@Override public Response<Long> geoadd(String key,Map<String,GeoCoordinate> memberCoordinateMap){
  getClient(key).geoadd(key,memberCoordinateMap);
  return getResponse(BuilderFactory.LONG);
}
