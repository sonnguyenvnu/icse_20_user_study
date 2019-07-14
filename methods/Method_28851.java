@Override public Response<Long> pfcount(byte[] key){
  getClient(key).pfcount(key);
  return getResponse(BuilderFactory.LONG);
}
