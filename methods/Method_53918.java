/** 
 * Unsafe version of  {@link #c3(float) c3}. 
 */
public static void nc3(long struct,float value){
  UNSAFE.putFloat(null,struct + AIMatrix3x3.C3,value);
}
