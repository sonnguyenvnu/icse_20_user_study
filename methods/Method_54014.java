/** 
 * Sets the specified value to the  {@code mNumBones} field of the specified {@code struct}. 
 */
public static void nmNumBones(long struct,int value){
  UNSAFE.putInt(null,struct + AIMesh.MNUMBONES,value);
}
