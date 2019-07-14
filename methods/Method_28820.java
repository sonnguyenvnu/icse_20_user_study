public Response<String> bgrewriteaof(){
  client.bgrewriteaof();
  return getResponse(BuilderFactory.STRING);
}
