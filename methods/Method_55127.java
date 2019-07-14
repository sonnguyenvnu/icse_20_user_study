/** 
 * Unsafe version of  {@link #depth}. 
 */
public static int ndepth(long struct){
  return UNSAFE.getInt(null,struct + XVisualInfo.DEPTH);
}
