/** 
 * Unsafe version of:  {@link #ClipCursor} 
 */
public static int nClipCursor(long rect){
  long __functionAddress=Functions.ClipCursor;
  return callPI(rect,__functionAddress);
}
