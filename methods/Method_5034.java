/** 
 * Returns whether the cursor is pointing to the position after the last DownloadState. 
 */
default boolean isAfterLast(){
  if (getCount() == 0) {
    return true;
  }
  return getPosition() == getCount();
}
