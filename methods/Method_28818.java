public Response<Set<String>> sunion(String... keys){
  client.sunion(keys);
  return getResponse(BuilderFactory.STRING_SET);
}
