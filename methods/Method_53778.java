/** 
 * Unsafe version of  {@link #mTextureCoords}. 
 */
public static PointerBuffer nmTextureCoords(long struct){
  return memPointerBuffer(struct + AIAnimMesh.MTEXTURECOORDS,Assimp.AI_MAX_NUMBER_OF_TEXTURECOORDS);
}
