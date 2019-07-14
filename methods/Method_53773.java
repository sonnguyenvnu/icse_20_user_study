/** 
 * Unsafe version of  {@link #mNormals}. 
 */
@Nullable public static AIVector3D.Buffer nmNormals(long struct){
  return AIVector3D.createSafe(memGetAddress(struct + AIAnimMesh.MNORMALS),nmNumVertices(struct));
}
