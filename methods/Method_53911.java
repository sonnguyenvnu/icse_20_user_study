/** 
 * Unsafe version of  {@link #a1(float) a1}. 
 */
public static void na1(long struct,float value){
  UNSAFE.putFloat(null,struct + AIMatrix3x3.A1,value);
}
