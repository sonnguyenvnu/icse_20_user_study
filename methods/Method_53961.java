/** 
 * Unsafe version of  {@link #d2(float) d2}. 
 */
public static void nd2(long struct,float value){
  UNSAFE.putFloat(null,struct + AIMatrix4x4.D2,value);
}
