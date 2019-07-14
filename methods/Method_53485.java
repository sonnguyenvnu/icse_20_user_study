/** 
 * check if any listener is set. Throws IllegalStateException if no listener is set.
 * @throws IllegalStateException when no listener is set.
 */
private void ensureStatusStreamListenerIsSet(){
  if (streamListeners.size() == 0) {
    throw new IllegalStateException("StatusListener is not set.");
  }
}
