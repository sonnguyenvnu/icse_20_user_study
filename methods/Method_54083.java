/** 
 * Sets the specified value to the  {@code mNumMaterials} field of the specified {@code struct}. 
 */
public static void nmNumMaterials(long struct,int value){
  UNSAFE.putInt(null,struct + AIScene.MNUMMATERIALS,value);
}
