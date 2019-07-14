/** 
 * Creates new case-insensitive map.
 */
public static <T>HttpMultiMap<T> newCaseSensitiveMap(){
  return new HttpMultiMap<>(true);
}
