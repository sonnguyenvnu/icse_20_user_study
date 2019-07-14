/** 
 * Sets the specified value to the  {@code mNumKeys} field of the specified {@code struct}. 
 */
public static void nmNumKeys(long struct,int value){
  UNSAFE.putInt(null,struct + AIMeshMorphAnim.MNUMKEYS,value);
}
