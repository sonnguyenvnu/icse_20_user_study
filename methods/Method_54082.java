/** 
 * Sets the specified value to the  {@code mNumMeshes} field of the specified {@code struct}. 
 */
public static void nmNumMeshes(long struct,int value){
  UNSAFE.putInt(null,struct + AIScene.MNUMMESHES,value);
}
