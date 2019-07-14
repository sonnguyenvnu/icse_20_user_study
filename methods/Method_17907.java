/** 
 * Returns true if scrapItems is not null and contains an item with key index.
 */
static <T>boolean existsScrapItemAt(int index,SparseArrayCompat<T> scrapItems){
  return scrapItems != null && scrapItems.get(index) != null;
}
