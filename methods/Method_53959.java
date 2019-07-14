/** 
 * Unsafe version of  {@link #c2(float) c2}. 
 */
public static void nc2(long struct,float value){
  UNSAFE.putFloat(null,struct + AIMatrix4x4.C2,value);
}
