/** 
 * Gets a key for an injection type and an annotation type.
 */
public static <T>Key<T> get(Class<T> type,Class<? extends Annotation> annotationType){
  return new Key<>(type,strategyFor(annotationType));
}
