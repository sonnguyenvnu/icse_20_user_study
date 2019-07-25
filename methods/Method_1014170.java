/** 
 * Deserializes and instantiates an object of type  {@code T} out of the givenJSON String. A special classloader (other than the one of the Json bundle) is used in order to load the classes in the context of the calling bundle.
 */
@SuppressWarnings("unchecked") private @Nullable T deserialize(@Nullable StorageEntry entry){
  if (entry == null) {
    return null;
  }
  @Nullable T value=null;
  try {
    Class<T> loadedValueType=null;
    if (classLoader == null) {
      loadedValueType=(Class<T>)Class.forName(entry.getEntityClassName());
    }
 else {
      loadedValueType=(Class<T>)classLoader.loadClass(entry.getEntityClassName());
    }
    value=entityMapper.fromJson((JsonElement)entry.getValue(),loadedValueType);
    logger.trace("deserialized value '{}' from Json",value);
  }
 catch (  Exception e) {
    logger.error("Couldn't deserialize value '{}'. Root cause is: {}",entry,e.getMessage());
  }
  return value;
}
