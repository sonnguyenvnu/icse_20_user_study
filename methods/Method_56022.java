/** 
 * Unsafe version of  {@link #maxThreadsDim(int,int) maxThreadsDim}. 
 */
public static void nmaxThreadsDim(long struct,int index,int value){
  UNSAFE.putInt(null,struct + CUdevprop.MAXTHREADSDIM + check(index,3) * 4,value);
}
