/** 
 * Deserializes and instantiates an object of type  {@code T} out of thegiven JSON String. A special classloader (other than the one of the MapDB bundle) is used in order to load the classes in the context of the calling bundle.
 * @param json
 * @return
 */
@SuppressWarnings("unchecked") public @Nullable T deserialize(@Nullable String json){
  if (json == null) {
    return null;
  }
  String[] concatValue=json.split(TYPE_SEPARATOR);
  String valueTypeName=concatValue[0];
  String valueAsString=concatValue[1];
  @Nullable T value=null;
  try {
    Class<T> loadedValueType=null;
    if (classLoader == null) {
      loadedValueType=(Class<T>)Class.forName(valueTypeName);
    }
 else {
      loadedValueType=(Class<T>)classLoader.loadClass(valueTypeName);
    }
    value=mapper.fromJson(valueAsString,loadedValueType);
    logger.trace("deserialized value '{}' from MapDB",value);
  }
 catch (  Exception e) {
    logger.warn("Couldn't deserialize value '{}'. Root cause is: {}",json,e.getMessage());
  }
  return value;
}
