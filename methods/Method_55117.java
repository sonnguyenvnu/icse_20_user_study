/** 
 * Unsafe version of  {@link #do_not_propagate_mask(long) do_not_propagate_mask}. 
 */
public static void ndo_not_propagate_mask(long struct,long value){
  memPutAddress(struct + XSetWindowAttributes.DO_NOT_PROPAGATE_MASK,value);
}
