/** 
 * Checks if RecyclerView is in the middle of a layout or scroll and throws an {@link IllegalStateException} if it <b>is not</b>.
 * @param message The message for the exception. Can be null.
 * @see #assertNotInLayoutOrScroll(String)
 */
void assertInLayoutOrScroll(String message){
  if (!isComputingLayout()) {
    if (message == null) {
      throw new IllegalStateException("Cannot call this method unless RecyclerView is " + "computing a layout or scrolling" + exceptionLabel());
    }
    throw new IllegalStateException(message + exceptionLabel());
  }
}
