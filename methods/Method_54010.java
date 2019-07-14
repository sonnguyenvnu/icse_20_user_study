/** 
 * Unsafe version of  {@link #mTextureCoords(PointerBuffer) mTextureCoords}. 
 */
public static void nmTextureCoords(long struct,PointerBuffer value){
  if (CHECKS) {
    checkGT(value,Assimp.AI_MAX_NUMBER_OF_TEXTURECOORDS);
  }
  memCopy(memAddress(value),struct + AIMesh.MTEXTURECOORDS,value.remaining() * POINTER_SIZE);
}
