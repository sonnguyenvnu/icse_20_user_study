/** 
 * Move the cursor to the first DownloadState. <p>This method will return false if the cursor is empty.
 * @return whether the move succeeded.
 */
default boolean moveToFirst(){
  return moveToPosition(0);
}
