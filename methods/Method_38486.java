/** 
 * Creates new case-insensitive multimap.
 */
public static <T>HttpMultiMap<T> newCaseInsensitiveMap(){
  return new HttpMultiMap<>(false);
}
