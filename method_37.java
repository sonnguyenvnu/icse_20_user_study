/** 
 * Returns a lookup which looks up values using a map. <p> If the map is null, then null will be returned from every lookup. The map result object is converted to a string using toString().
 * @param < V > the type of the values supported by the lookup
 * @param map  the map of keys to values, may be null
 * @return a lookup using the map, not null
 */
public static <V>StrLookup<V> _XXXXX_(final Map<String,V> map){
  return new MapStrLookup<>(map);
}