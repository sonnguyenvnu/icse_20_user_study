/** 
 * Unsafe version of  {@link #mBitangents(AIVector3D.Buffer) mBitangents}. 
 */
public static void nmBitangents(long struct,@Nullable AIVector3D.Buffer value){
  memPutAddress(struct + AIAnimMesh.MBITANGENTS,memAddressSafe(value));
}
