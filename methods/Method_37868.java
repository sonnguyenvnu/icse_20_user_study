/** 
 * Finds constructor with given parameter types. First matched ctor is returned.
 */
public static <T>Constructor<T> findConstructor(final Class<T> clazz,final Class<?>... parameterTypes){
  final Constructor<?>[] constructors=clazz.getConstructors();
  Class<?>[] pts;
  for (  Constructor<?> constructor : constructors) {
    pts=constructor.getParameterTypes();
    if (isAllAssignableFrom(pts,parameterTypes)) {
      return (Constructor<T>)constructor;
    }
  }
  return null;
}
