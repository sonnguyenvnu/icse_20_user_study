/** 
 * Gets a key for an injection type and an annotation.
 */
public static <T>Key<T> get(Class<T> type,Annotation annotation){
  return new Key<>(type,strategyFor(annotation));
}
