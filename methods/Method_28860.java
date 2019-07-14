@Override public Response<List<GeoCoordinate>> geopos(byte[] key,byte[]... members){
  getClient(key).geopos(key,members);
  return getResponse(BuilderFactory.GEO_COORDINATE_LIST);
}
