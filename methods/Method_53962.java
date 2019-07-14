/** 
 * Unsafe version of  {@link #d3(float) d3}. 
 */
public static void nd3(long struct,float value){
  UNSAFE.putFloat(null,struct + AIMatrix4x4.D3,value);
}
