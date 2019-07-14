/** 
 * Creates new collection of target component type. Default implementation uses reflection to create an collection of target type. Override it for better performances.
 */
@SuppressWarnings("unchecked") protected Collection<T> createCollection(final int length){
  if (collectionType.isInterface()) {
    if (collectionType == List.class) {
      if (length > 0) {
        return new ArrayList<>(length);
      }
 else {
        return new ArrayList<>();
      }
    }
    if (collectionType == Set.class) {
      if (length > 0) {
        return new HashSet<>(length);
      }
 else {
        return new HashSet<>();
      }
    }
    throw new TypeConversionException("Unknown collection: " + collectionType.getName());
  }
  if (length > 0) {
    try {
      Constructor<Collection<T>> ctor=(Constructor<Collection<T>>)collectionType.getConstructor(int.class);
      return ctor.newInstance(Integer.valueOf(length));
    }
 catch (    Exception ex) {
    }
  }
  try {
    return collectionType.getDeclaredConstructor().newInstance();
  }
 catch (  Exception ex) {
    throw new TypeConversionException(ex);
  }
}
