/** 
 * Unsafe version of  {@link #b4(float) b4}. 
 */
public static void nb4(long struct,float value){
  UNSAFE.putFloat(null,struct + AIMatrix4x4.B4,value);
}
