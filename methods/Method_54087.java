/** 
 * Sets the specified value to the  {@code mNumTextures} field of the specified {@code struct}. 
 */
public static void nmNumTextures(long struct,int value){
  UNSAFE.putInt(null,struct + AIScene.MNUMTEXTURES,value);
}
