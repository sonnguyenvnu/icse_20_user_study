@Override public Response<String> type(byte[] key){
  getClient(key).type(key);
  return getResponse(BuilderFactory.STRING);
}
