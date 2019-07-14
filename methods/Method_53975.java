/** 
 * Unsafe version of  {@link #nodes}. 
 */
public static int nnodes(long struct){
  return UNSAFE.getInt(null,struct + AIMemoryInfo.NODES);
}
