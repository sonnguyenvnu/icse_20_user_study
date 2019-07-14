/** 
 * Moves an item from oldIndex to newIndex. The item is taken from scrapitems if an item exists in scrapItems at oldPosition. Otherwise the item is taken from items. This assumes that there is no item at newIndex for the items array. If that's the case {@link ComponentHostUtils#scrapItemAt(int,SparseArrayCompat,SparseArrayCompat)}has to be called before invoking this.
 */
static <T>void moveItem(int oldIndex,int newIndex,SparseArrayCompat<T> items,SparseArrayCompat<T> scrapItems){
  T itemToMove;
  if (existsScrapItemAt(oldIndex,scrapItems)) {
    itemToMove=scrapItems.get(oldIndex);
    scrapItems.remove(oldIndex);
  }
 else {
    itemToMove=items.get(oldIndex);
    items.remove(oldIndex);
  }
  items.put(newIndex,itemToMove);
}
