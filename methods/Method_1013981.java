/** 
 * Returns a collection of all values - possibly from the cache, if they are still valid.
 * @return the collection of all values
 */
public synchronized Collection<@Nullable V> values(){
  final Collection<@Nullable V> values=new LinkedList<>();
  for (  final ExpiringCache<@Nullable V> item : items.values()) {
    values.add(item.getValue());
  }
  return values;
}
