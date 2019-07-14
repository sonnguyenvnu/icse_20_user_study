/** 
 * Sets the specified value to the  {@code mNumAnimations} field of the specified {@code struct}. 
 */
public static void nmNumAnimations(long struct,int value){
  UNSAFE.putInt(null,struct + AIScene.MNUMANIMATIONS,value);
}
