public Response<String> bgsave(){
  client.bgsave();
  return getResponse(BuilderFactory.STRING);
}
