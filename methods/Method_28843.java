@Override public Response<Long> hsetnx(String key,String field,String value){
  getClient(key).hsetnx(key,field,value);
  return getResponse(BuilderFactory.LONG);
}
