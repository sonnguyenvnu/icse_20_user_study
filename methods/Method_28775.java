@Override public List<GeoCoordinate> geopos(String key,String... members){
  checkIsInMultiOrPipeline();
  client.geopos(key,members);
  return BuilderFactory.GEO_COORDINATE_LIST.build(client.getObjectMultiBulkReply());
}
