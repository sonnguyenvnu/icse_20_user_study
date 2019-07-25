/** 
 * Clears the iterator using its remove method. 
 */
static void clear(Iterator<?> iterator){
  checkNotNull(iterator);
  while (iterator.hasNext()) {
    iterator.next();
    iterator.remove();
  }
}
