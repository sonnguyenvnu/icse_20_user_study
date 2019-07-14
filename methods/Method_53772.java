/** 
 * Unsafe version of  {@link #mVertices}. 
 */
@Nullable public static AIVector3D.Buffer nmVertices(long struct){
  return AIVector3D.createSafe(memGetAddress(struct + AIAnimMesh.MVERTICES),nmNumVertices(struct));
}
