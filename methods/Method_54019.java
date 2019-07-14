/** 
 * Unsafe version of  {@link #mAnimMeshes(PointerBuffer) mAnimMeshes}. 
 */
public static void nmAnimMeshes(long struct,@Nullable PointerBuffer value){
  memPutAddress(struct + AIMesh.MANIMMESHES,memAddressSafe(value));
  nmNumAnimMeshes(struct,value == null ? 0 : value.remaining());
}
