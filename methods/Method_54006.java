/** 
 * Unsafe version of  {@link #mTangents(AIVector3D.Buffer) mTangents}. 
 */
public static void nmTangents(long struct,@Nullable AIVector3D.Buffer value){
  memPutAddress(struct + AIMesh.MTANGENTS,memAddressSafe(value));
}
