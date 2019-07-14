/** 
 * Unsafe version of  {@link #a3(float) a3}. 
 */
public static void na3(long struct,float value){
  UNSAFE.putFloat(null,struct + AIMatrix4x4.A3,value);
}
