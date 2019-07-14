/** 
 * Closes the reference handling null.
 * @param ref the reference to close
 */
public static void closeSafely(@Nullable CloseableReference<?> ref){
  if (ref != null) {
    ref.close();
  }
}
