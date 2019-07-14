/** 
 * Unsafe version of  {@link #c2(float) c2}. 
 */
public static void nc2(long struct,float value){
  UNSAFE.putFloat(null,struct + AIMatrix3x3.C2,value);
}
