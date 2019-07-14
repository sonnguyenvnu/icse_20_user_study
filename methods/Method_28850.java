public Response<Long> pttl(byte[] key){
  getClient(key).pttl(key);
  return getResponse(BuilderFactory.LONG);
}
