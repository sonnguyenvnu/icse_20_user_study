/** 
 * Creates new type for JSON array objects. It returns a collection. Later, the collection will be converted into the target type.
 */
@SuppressWarnings("unchecked") protected Collection<Object> newArrayInstance(final Class targetType){
  if (targetType == null || targetType == List.class || targetType == Collection.class || targetType.isArray()) {
    return listSupplier.get();
  }
  if (targetType == Set.class) {
    return new HashSet<>();
  }
  try {
    return (Collection<Object>)targetType.getDeclaredConstructor().newInstance();
  }
 catch (  Exception e) {
    throw new JsonException(e);
  }
}
