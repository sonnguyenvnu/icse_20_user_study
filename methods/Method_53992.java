/** 
 * Unsafe version of  {@link #mBitangents}. 
 */
@Nullable public static AIVector3D.Buffer nmBitangents(long struct){
  return AIVector3D.createSafe(memGetAddress(struct + AIMesh.MBITANGENTS),nmNumVertices(struct));
}
