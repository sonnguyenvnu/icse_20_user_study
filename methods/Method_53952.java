/** 
 * Unsafe version of  {@link #a2(float) a2}. 
 */
public static void na2(long struct,float value){
  UNSAFE.putFloat(null,struct + AIMatrix4x4.A2,value);
}
