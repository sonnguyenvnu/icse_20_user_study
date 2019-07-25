/** 
 * Gets a key for an injection type and an annotation type.
 */
public static Key<?> get(Type type,Class<? extends Annotation> annotationType){
  return new Key<Object>(type,strategyFor(annotationType));
}
