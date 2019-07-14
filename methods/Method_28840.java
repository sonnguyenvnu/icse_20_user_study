@Override public Response<Boolean> getbit(String key,long offset){
  getClient(key).getbit(key,offset);
  return getResponse(BuilderFactory.BOOLEAN);
}
