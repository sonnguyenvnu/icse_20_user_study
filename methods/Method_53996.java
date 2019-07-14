/** 
 * Unsafe version of  {@link #mNumUVComponents}. 
 */
public static IntBuffer nmNumUVComponents(long struct){
  return memIntBuffer(struct + AIMesh.MNUMUVCOMPONENTS,Assimp.AI_MAX_NUMBER_OF_TEXTURECOORDS);
}
