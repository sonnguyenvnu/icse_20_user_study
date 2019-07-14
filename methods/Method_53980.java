/** 
 * Returns a  {@link IntBuffer} view of the {@code mNumUVComponents} field. 
 */
@NativeType("unsigned int[Assimp.AI_MAX_NUMBER_OF_TEXTURECOORDS]") public IntBuffer mNumUVComponents(){
  return nmNumUVComponents(address());
}
