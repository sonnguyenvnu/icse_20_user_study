/** 
 * Find an entry by key.
 * @param key the key (may not exist)
 * @return the matching or next index
 */
int find(long key){
  int l=0, r=entryCount;
  while (l < r) {
    int i=(l + r) >>> 1;
    long k=keys[i];
    if (k == key) {
      return i;
    }
 else     if (k > key) {
      r=i;
    }
 else {
      l=i + 1;
    }
  }
  return l;
}
