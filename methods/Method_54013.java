/** 
 * Unsafe version of  {@link #mNumUVComponents(int,int) mNumUVComponents}. 
 */
public static void nmNumUVComponents(long struct,int index,int value){
  UNSAFE.putInt(null,struct + AIMesh.MNUMUVCOMPONENTS + check(index,Assimp.AI_MAX_NUMBER_OF_TEXTURECOORDS) * 4,value);
}
