/** 
 * Unsafe version of  {@link #mChildren(PointerBuffer) mChildren}. 
 */
public static void nmChildren(long struct,@Nullable PointerBuffer value){
  memPutAddress(struct + AINode.MCHILDREN,memAddressSafe(value));
  nmNumChildren(struct,value == null ? 0 : value.remaining());
}
