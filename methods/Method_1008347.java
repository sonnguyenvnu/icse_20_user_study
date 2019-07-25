/** 
 * Gets a key for an injection type and an annotation.
 */
public static Key<?> get(Type type,Annotation annotation){
  return new Key<Object>(type,strategyFor(annotation));
}
