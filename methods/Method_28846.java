@Override public Response<Boolean> setbit(byte[] key,long offset,byte[] value){
  getClient(key).setbit(key,offset,value);
  return getResponse(BuilderFactory.BOOLEAN);
}
