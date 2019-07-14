@Override public Response<Long> pfcount(String key){
  getClient(key).pfcount(key);
  return getResponse(BuilderFactory.LONG);
}
