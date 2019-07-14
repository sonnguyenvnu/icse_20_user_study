/** 
 * Copies the specified  {@link IntBuffer} to the {@code mNumUVComponents} field. 
 */
public AIMesh mNumUVComponents(@NativeType("unsigned int[Assimp.AI_MAX_NUMBER_OF_TEXTURECOORDS]") IntBuffer value){
  nmNumUVComponents(address(),value);
  return this;
}
