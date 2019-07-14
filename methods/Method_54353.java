/** 
 * Unsafe version of  {@link #view}. 
 */
public static short nview(long struct){
  return UNSAFE.getShort(null,struct + BGFXViewStats.VIEW);
}
