/** 
 * Removes the exclusively owned items until there is at most <code>count</code> of them and they occupy no more than <code>size</code> bytes. <p> This method returns the removed items instead of actually closing them, so it is safe to be called while holding the <code>this</code> lock.
 */
@Nullable private synchronized ArrayList<Entry<K,V>> trimExclusivelyOwnedEntries(int count,int size){
  count=Math.max(count,0);
  size=Math.max(size,0);
  if (mExclusiveEntries.getCount() <= count && mExclusiveEntries.getSizeInBytes() <= size) {
    return null;
  }
  ArrayList<Entry<K,V>> oldEntries=new ArrayList<>();
  while (mExclusiveEntries.getCount() > count || mExclusiveEntries.getSizeInBytes() > size) {
    K key=mExclusiveEntries.getFirstKey();
    mExclusiveEntries.remove(key);
    oldEntries.add(mCachedEntries.remove(key));
  }
  return oldEntries;
}
