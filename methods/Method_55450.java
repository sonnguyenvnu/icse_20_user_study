/** 
 * Prints the specified message to the  {@link #DEBUG_STREAM} if {@link Checks#DEBUG} is true.
 * @param msg the message to print
 */
public static void apiLog(@Nullable CharSequence msg){
  if (DEBUG) {
    DEBUG_STREAM.print("[LWJGL] ");
    DEBUG_STREAM.println(msg);
  }
}
