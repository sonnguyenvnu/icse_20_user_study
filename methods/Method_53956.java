/** 
 * Unsafe version of  {@link #b3(float) b3}. 
 */
public static void nb3(long struct,float value){
  UNSAFE.putFloat(null,struct + AIMatrix4x4.B3,value);
}
