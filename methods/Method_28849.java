public Response<Long> pttl(String key){
  getClient(key).pttl(key);
  return getResponse(BuilderFactory.LONG);
}
