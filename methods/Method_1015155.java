/** 
 * @return a set representing all keys in the map
 */
default ISet<K> keys(){
  return Sets.from(Lists.lazyMap(entries(),IEntry::key),this::contains);
}
