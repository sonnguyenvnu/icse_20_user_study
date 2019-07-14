/** 
 * Sets the specified value to the  {@code mNumCameras} field of the specified {@code struct}. 
 */
public static void nmNumCameras(long struct,int value){
  UNSAFE.putInt(null,struct + AIScene.MNUMCAMERAS,value);
}
