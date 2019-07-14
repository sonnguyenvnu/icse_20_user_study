/** 
 * Unsafe version of  {@link #mBones(PointerBuffer) mBones}. 
 */
public static void nmBones(long struct,@Nullable PointerBuffer value){
  memPutAddress(struct + AIMesh.MBONES,memAddressSafe(value));
  nmNumBones(struct,value == null ? 0 : value.remaining());
}
