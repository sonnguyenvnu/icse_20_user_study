/** 
 * Equivalent to calling  {@link #clear()} followed by {@link #addAll(Collection)}, but this should make it more apparent that this is an optimized code path. Instead of needing lots of O(log2 N) operations, this special case is optimized to perform in O(N) time, where N is the size of the incoming collection.
 */
public void clearAndAddAll(ArraySet<? extends E> collection){
  clear();
  addAll(collection);
}
