/** 
 * Unsafe version of  {@link #mFlags}. 
 */
public static int nmFlags(long struct){
  return UNSAFE.getInt(null,struct + AIScene.MFLAGS);
}
