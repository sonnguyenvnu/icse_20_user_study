/** 
 * Unsafe version of  {@link #dwFlags}. 
 */
public static int ndwFlags(long struct){
  return UNSAFE.getInt(null,struct + MONITORINFOEX.DWFLAGS);
}
