/** 
 * Unsafe version of  {@link #b2(float) b2}. 
 */
public static void nb2(long struct,float value){
  UNSAFE.putFloat(null,struct + AIMatrix3x3.B2,value);
}
