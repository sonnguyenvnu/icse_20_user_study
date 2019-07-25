/** 
 * Gets type literal for the given  {@code Class} instance.
 */
public static <T>TypeLiteral<T> get(Class<T> type){
  return new TypeLiteral<>(type);
}
