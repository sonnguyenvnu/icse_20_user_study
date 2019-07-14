@Override public Response<List<GeoCoordinate>> geopos(String key,String... members){
  getClient(key).geopos(key,members);
  return getResponse(BuilderFactory.GEO_COORDINATE_LIST);
}
