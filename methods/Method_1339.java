/** 
 * Method used for tracking Closeables pointed by CloseableReference. Use only for debugging and logging.
 */
public int getValueHash(){
  return isValid() ? System.identityHashCode(mSharedReference.get()) : 0;
}
