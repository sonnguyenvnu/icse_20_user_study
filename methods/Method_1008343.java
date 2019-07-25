/** 
 * Gets a key for an injection type.
 */
public static <T>Key<T> get(Class<T> type){
  return new Key<>(type,NullAnnotationStrategy.INSTANCE);
}
