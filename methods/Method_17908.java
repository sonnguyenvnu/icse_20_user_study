/** 
 * Remove the item at given  {@param index}. The item is removed from  {@param scrapItems} if theitem exists there at given index, otherwise it is removed from  {@param items}.
 */
static <T>void removeItem(int index,SparseArrayCompat<T> items,SparseArrayCompat<T> scrapItems){
  if (existsScrapItemAt(index,scrapItems)) {
    scrapItems.remove(index);
  }
 else {
    items.remove(index);
  }
}
