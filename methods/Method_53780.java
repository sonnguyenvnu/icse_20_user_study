/** 
 * Unsafe version of  {@link #mNormals(AIVector3D.Buffer) mNormals}. 
 */
public static void nmNormals(long struct,@Nullable AIVector3D.Buffer value){
  memPutAddress(struct + AIAnimMesh.MNORMALS,memAddressSafe(value));
}
