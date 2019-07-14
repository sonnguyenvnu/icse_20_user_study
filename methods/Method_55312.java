/** 
 * Unsafe version of  {@link #cbSize}. 
 */
public static int ncbSize(long struct){
  return UNSAFE.getInt(null,struct + MONITORINFOEX.CBSIZE);
}
