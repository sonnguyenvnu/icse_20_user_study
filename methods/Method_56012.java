/** 
 * Unsafe version of  {@link #maxThreadsDim(int) maxThreadsDim}. 
 */
public static int nmaxThreadsDim(long struct,int index){
  return UNSAFE.getInt(null,struct + CUdevprop.MAXTHREADSDIM + check(index,3) * 4);
}
