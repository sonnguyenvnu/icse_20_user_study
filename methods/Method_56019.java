/** 
 * Unsafe version of  {@link #clockRate}. 
 */
public static int nclockRate(long struct){
  return UNSAFE.getInt(null,struct + CUdevprop.CLOCKRATE);
}
