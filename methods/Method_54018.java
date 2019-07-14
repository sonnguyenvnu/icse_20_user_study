/** 
 * Sets the specified value to the  {@code mNumAnimMeshes} field of the specified {@code struct}. 
 */
public static void nmNumAnimMeshes(long struct,int value){
  UNSAFE.putInt(null,struct + AIMesh.MNUMANIMMESHES,value);
}
