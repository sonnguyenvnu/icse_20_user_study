/** 
 * Unsafe version of  {@link #bReserved}. 
 */
public static byte nbReserved(long struct){
  return UNSAFE.getByte(null,struct + PIXELFORMATDESCRIPTOR.BRESERVED);
}
