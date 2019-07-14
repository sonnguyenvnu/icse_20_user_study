/** 
 * Unsafe version of  {@link #c1(float) c1}. 
 */
public static void nc1(long struct,float value){
  UNSAFE.putFloat(null,struct + AIMatrix3x3.C1,value);
}
