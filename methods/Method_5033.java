/** 
 * Returns whether the cursor is pointing to the last DownloadState. 
 */
default boolean isLast(){
  int count=getCount();
  return getPosition() == (count - 1) && count != 0;
}
