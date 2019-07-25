/** 
 * Returns a  {@link TypedSpelPath} for the given source and type.
 * @param source must not be {@literal null}.
 * @return
 */
public static TypedSpelPath typed(String source,Class<?> type){
  return untyped(source).bindTo(type);
}
