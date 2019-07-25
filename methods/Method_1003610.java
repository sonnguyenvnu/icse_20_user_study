public DefaultConfigDataBuilder copy(){
  return new DefaultConfigDataBuilder(serverEnvironment,objectMapper,sources,errorHandler);
}
