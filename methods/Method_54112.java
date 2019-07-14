/** 
 * Unsafe version of  {@link #mVertexId}. 
 */
public static int nmVertexId(long struct){
  return UNSAFE.getInt(null,struct + AIVertexWeight.MVERTEXID);
}
