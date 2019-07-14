/** 
 * Sets the specified value to the  {@code mNumFaces} field of the specified {@code struct}. 
 */
public static void nmNumFaces(long struct,int value){
  UNSAFE.putInt(null,struct + AIMesh.MNUMFACES,value);
}
