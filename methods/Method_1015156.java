/** 
 * @return a list representing all values in the map
 */
default IList<V> values(){
  return Lists.lazyMap(entries(),IEntry::value);
}
