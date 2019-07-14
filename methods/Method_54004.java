/** 
 * Unsafe version of  {@link #mVertices(AIVector3D.Buffer) mVertices}. 
 */
public static void nmVertices(long struct,AIVector3D.Buffer value){
  memPutAddress(struct + AIMesh.MVERTICES,value.address());
}
