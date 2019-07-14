/** 
 * Unsafe version of  {@link #colormap_size}. 
 */
public static int ncolormap_size(long struct){
  return UNSAFE.getInt(null,struct + XVisualInfo.COLORMAP_SIZE);
}
