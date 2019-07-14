@Override public Response<Long> hincrBy(String key,String field,long value){
  getClient(key).hincrBy(key,field,value);
  return getResponse(BuilderFactory.LONG);
}
