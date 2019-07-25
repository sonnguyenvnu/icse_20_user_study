/** 
 * Unregister item.
 * @param item item
 */
public void remove(S item){
  ordered.remove(item);
  unordered.remove(item);
  Collections.sort(ordered,comparator);
  list.clear();
  list.addAll(ordered);
  list.addAll(unordered);
}
