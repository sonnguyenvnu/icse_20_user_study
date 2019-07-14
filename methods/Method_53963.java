/** 
 * Unsafe version of  {@link #d4(float) d4}. 
 */
public static void nd4(long struct,float value){
  UNSAFE.putFloat(null,struct + AIMatrix4x4.D4,value);
}
