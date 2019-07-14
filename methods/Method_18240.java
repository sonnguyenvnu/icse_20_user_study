/** 
 * Removes the mapping from the specified key, if there was any. 
 */
public void delete(int key){
  int i=binarySearch(mKeys,mSize,key);
  if (i >= 0) {
    removeAt(i);
  }
}
