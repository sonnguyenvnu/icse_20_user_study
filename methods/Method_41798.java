private static Object deserializeFromString(String key){
  try {
    return SerializationHelper.deserializeFromString(key);
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
catch (  ClassNotFoundException e) {
    throw new RuntimeException(e);
  }
}
