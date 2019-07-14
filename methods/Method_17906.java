/** 
 * Takes the item at position index from items and puts it into scrapItems. If no such item exists the invocation of this method will have no effect.
 */
static <T>void scrapItemAt(int index,SparseArrayCompat<T> items,SparseArrayCompat<T> scrapItems){
  if (items == null || scrapItems == null) {
    return;
  }
  final T value=items.get(index);
  if (value != null) {
    scrapItems.put(index,value);
  }
}
