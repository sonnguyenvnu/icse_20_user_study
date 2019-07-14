/** 
 * Unsafe version of  {@link #mMethod}. 
 */
public static int nmMethod(long struct){
  return UNSAFE.getInt(null,struct + AIMesh.MMETHOD);
}
