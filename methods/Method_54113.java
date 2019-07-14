/** 
 * Unsafe version of  {@link #mVertexId(int) mVertexId}. 
 */
public static void nmVertexId(long struct,int value){
  UNSAFE.putInt(null,struct + AIVertexWeight.MVERTEXID,value);
}
