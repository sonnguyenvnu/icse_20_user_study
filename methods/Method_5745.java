/** 
 * Returns a Collection of all CachedContent instances in the index. The collection is backed by the  {@code keyToContent} map, so changes to the map are reflected in the collection, andvice-versa. If the map is modified while an iteration over the collection is in progress (except through the iterator's own remove operation), the results of the iteration are undefined.
 */
public Collection<CachedContent> getAll(){
  return keyToContent.values();
}
