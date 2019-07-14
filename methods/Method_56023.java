/** 
 * Unsafe version of  {@link #maxGridSize(int,int) maxGridSize}. 
 */
public static void nmaxGridSize(long struct,int index,int value){
  UNSAFE.putInt(null,struct + CUdevprop.MAXGRIDSIZE + check(index,3) * 4,value);
}
