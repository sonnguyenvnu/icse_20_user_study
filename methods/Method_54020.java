/** 
 * Unsafe version of  {@link #mMethod(int) mMethod}. 
 */
public static void nmMethod(long struct,int value){
  UNSAFE.putInt(null,struct + AIMesh.MMETHOD,value);
}
