/** 
 * Unsafe version of  {@link #mTangents}. 
 */
@Nullable public static AIVector3D.Buffer nmTangents(long struct){
  return AIVector3D.createSafe(memGetAddress(struct + AIAnimMesh.MTANGENTS),nmNumVertices(struct));
}
