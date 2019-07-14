@Override public Response<String> type(String key){
  getClient(key).type(key);
  return getResponse(BuilderFactory.STRING);
}
