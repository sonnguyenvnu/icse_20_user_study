@Override public Response<Boolean> setbit(String key,long offset,boolean value){
  getClient(key).setbit(key,offset,value);
  return getResponse(BuilderFactory.BOOLEAN);
}
