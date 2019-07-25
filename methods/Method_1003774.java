/** 
 * Intern the given type token.
 * @param typeToken the type token to intern
 * @param < T > the type
 * @return the given type token interned
 * @since 1.5
 */
public static <T>TypeToken<T> intern(TypeToken<T> typeToken){
  return TypeCaching.typeToken(typeToken);
}
