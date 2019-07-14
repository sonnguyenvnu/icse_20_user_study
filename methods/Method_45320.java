public static <T>T toObject(final String value,final Class<T> clazz){
  try {
    return DEFAULT_MAPPER.readValue(value,clazz);
  }
 catch (  IOException e) {
    throw new MocoException(e);
  }
}
