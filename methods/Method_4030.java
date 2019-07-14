/** 
 * Replaces the current items with the new items, dispatching  {@link ListUpdateCallback} eventsfor each change detected as appropriate. Does not modify or retain the input.
 * @see #replaceAll(T[],boolean)
 * @param items Array of items to replace current items.
 */
public void replaceAll(@NonNull Collection<T> items){
  T[] copy=(T[])Array.newInstance(mTClass,items.size());
  replaceAll(items.toArray(copy),true);
}
