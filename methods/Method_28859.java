@Override public Response<List<String>> geohash(String key,String... members){
  getClient(key).geohash(key,members);
  return getResponse(BuilderFactory.STRING_LIST);
}
