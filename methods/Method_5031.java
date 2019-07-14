/** 
 * Move the cursor to the previous DownloadState. <p>This method will return false if the cursor is already before the first entry in the result set.
 * @return whether the move succeeded.
 */
default boolean moveToPrevious(){
  return moveToPosition(getPosition() - 1);
}
