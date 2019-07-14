/** 
 * Unsafe version of  {@link #a4(float) a4}. 
 */
public static void na4(long struct,float value){
  UNSAFE.putFloat(null,struct + AIMatrix4x4.A4,value);
}
