/** 
 * Unsafe version of  {@link #clockRate(int) clockRate}. 
 */
public static void nclockRate(long struct,int value){
  UNSAFE.putInt(null,struct + CUdevprop.CLOCKRATE,value);
}
