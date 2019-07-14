/** 
 * Returns whether the cursor is pointing to the first DownloadState. 
 */
default boolean isFirst(){
  return getPosition() == 0 && getCount() != 0;
}
