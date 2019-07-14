public Response<Set<String>> sinter(String... keys){
  client.sinter(keys);
  return getResponse(BuilderFactory.STRING_SET);
}
