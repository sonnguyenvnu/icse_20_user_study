@Override public Response<Long> geoadd(byte[] key,Map<byte[],GeoCoordinate> memberCoordinateMap){
  getClient(key).geoadd(key,memberCoordinateMap);
  return getResponse(BuilderFactory.LONG);
}
