public Response<String> configSet(String parameter,String value){
  client.configSet(parameter,value);
  return getResponse(BuilderFactory.STRING);
}
