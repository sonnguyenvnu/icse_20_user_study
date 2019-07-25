@Override public <T>T decode(String data,Class<T> clazz){
  try {
    return objectMapper.readValue(data,clazz);
  }
 catch (  IOException e) {
    throw new IllegalStateException("decode error " + data,e);
  }
}
