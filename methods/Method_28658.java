@Override public List<GeoCoordinate> geopos(byte[] key,byte[]... members){
  checkIsInMultiOrPipeline();
  client.geopos(key,members);
  return BuilderFactory.GEO_COORDINATE_LIST.build(client.getObjectMultiBulkReply());
}
