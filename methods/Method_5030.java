/** 
 * Move the cursor to the next DownloadState. <p>This method will return false if the cursor is already past the last entry in the result set.
 * @return whether the move succeeded.
 */
default boolean moveToNext(){
  return moveToPosition(getPosition() + 1);
}
