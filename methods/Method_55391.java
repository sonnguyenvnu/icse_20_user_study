/** 
 * Unsafe version of  {@link #nLength(int) nLength}. 
 */
public static void nnLength(long struct,int value){
  UNSAFE.putInt(null,struct + SECURITY_ATTRIBUTES.NLENGTH,value);
}
