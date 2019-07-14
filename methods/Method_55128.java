/** 
 * Unsafe version of  {@link #green_mask}. 
 */
public static long ngreen_mask(long struct){
  return memGetAddress(struct + XVisualInfo.GREEN_MASK);
}
