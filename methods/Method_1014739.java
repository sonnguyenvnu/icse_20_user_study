private <T>T deserialize(byte[] value,Class<T> clazz){
  try {
    return objectMapper.readValue(value,clazz);
  }
 catch (  Exception e) {
    log.error(e.getMessage(),e);
  }
  return null;
}
