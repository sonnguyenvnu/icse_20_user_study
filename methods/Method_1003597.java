/** 
 * Creates a factory that always returns the given item.
 * @param item the item to always provide
 * @param < T > the type of the item
 * @return a factory that always returns {@code item}
 * @since 1.1
 */
static <T>Factory<T> constant(T item){
  return () -> item;
}
