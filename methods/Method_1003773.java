/** 
 * Create a type token for the given class. This method should be preferred over  {@link TypeToken#of(Class)} as the result may be interned when not in development.
 * @param clazz the class
 * @param < T > the type
 * @return a type token for the given class
 * @since 1.1
 */
public static <T>TypeToken<T> token(Class<T> clazz){
  return TypeCaching.typeToken(clazz);
}
