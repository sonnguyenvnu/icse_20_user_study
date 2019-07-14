/** 
 * Unsafe version of  {@link #do_not_propagate_mask}. 
 */
public static long ndo_not_propagate_mask(long struct){
  return memGetAddress(struct + XSetWindowAttributes.DO_NOT_PROPAGATE_MASK);
}
