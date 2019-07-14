/** 
 * Unsafe version of  {@link #maxGridSize(int) maxGridSize}. 
 */
public static int nmaxGridSize(long struct,int index){
  return UNSAFE.getInt(null,struct + CUdevprop.MAXGRIDSIZE + check(index,3) * 4);
}
