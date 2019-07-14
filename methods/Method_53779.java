/** 
 * Unsafe version of  {@link #mVertices(AIVector3D.Buffer) mVertices}. 
 */
public static void nmVertices(long struct,@Nullable AIVector3D.Buffer value){
  memPutAddress(struct + AIAnimMesh.MVERTICES,memAddressSafe(value));
}
