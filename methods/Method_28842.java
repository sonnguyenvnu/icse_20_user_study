@Override public Response<Long> hincrBy(byte[] key,byte[] field,long value){
  getClient(key).hincrBy(key,field,value);
  return getResponse(BuilderFactory.LONG);
}
