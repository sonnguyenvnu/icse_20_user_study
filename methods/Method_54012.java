/** 
 * Unsafe version of  {@link #mNumUVComponents(IntBuffer) mNumUVComponents}. 
 */
public static void nmNumUVComponents(long struct,IntBuffer value){
  if (CHECKS) {
    checkGT(value,Assimp.AI_MAX_NUMBER_OF_TEXTURECOORDS);
  }
  memCopy(memAddress(value),struct + AIMesh.MNUMUVCOMPONENTS,value.remaining() * 4);
}
