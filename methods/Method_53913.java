/** 
 * Unsafe version of  {@link #b1(float) b1}. 
 */
public static void nb1(long struct,float value){
  UNSAFE.putFloat(null,struct + AIMatrix3x3.B1,value);
}
