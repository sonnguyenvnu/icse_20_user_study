/** 
 * Checks if RecyclerView is in the middle of a layout or scroll and throws an {@link IllegalStateException} if it <b>is</b>.
 * @param message The message for the exception. Can be null.
 * @see #assertInLayoutOrScroll(String)
 */
void assertNotInLayoutOrScroll(String message){
  if (isComputingLayout()) {
    if (message == null) {
      throw new IllegalStateException("Cannot call this method while RecyclerView is " + "computing a layout or scrolling" + exceptionLabel());
    }
    throw new IllegalStateException(message);
  }
  if (mDispatchScrollCounter > 0) {
    Log.w(TAG,"Cannot call this method in a scroll callback. Scroll callbacks might" + "be run during a measure & layout pass where you cannot change the" + "RecyclerView data. Any method call that might change the structure" + "of the RecyclerView or the adapter contents should be postponed to" + "the next frame.",new IllegalStateException("" + exceptionLabel()));
  }
}
