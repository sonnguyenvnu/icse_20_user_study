/** 
 * Returns a Perhaps that signals the given item.
 * @param < T > the value type
 * @param item the item to signal, not null
 * @return the new Perhaps instance
 */
public static <T>Perhaps<T> just(T item){
  ObjectHelper.requireNonNull(item,"item is null");
  return onAssembly(new PerhapsJust<T>(item));
}
