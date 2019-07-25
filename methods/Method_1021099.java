private String serialize(final Object state){
  try {
    return objectMapper.writeValueAsString(state);
  }
 catch (  JsonProcessingException e) {
    LOG.error("failed to publish audit log",e);
    return null;
  }
}
