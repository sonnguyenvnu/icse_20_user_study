/** 
 * Unsafe version of  {@link #rendererType}. 
 */
public static int nrendererType(long struct){
  return UNSAFE.getInt(null,struct + BGFXCaps.RENDERERTYPE);
}
