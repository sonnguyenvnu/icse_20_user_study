/** 
 * Sets the specified value to the  {@code mNumLights} field of the specified {@code struct}. 
 */
public static void nmNumLights(long struct,int value){
  UNSAFE.putInt(null,struct + AIScene.MNUMLIGHTS,value);
}
