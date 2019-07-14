/** 
 * Unsafe version of  {@link #dmReserved1}. 
 */
public static int ndmReserved1(long struct){
  return UNSAFE.getInt(null,struct + DEVMODE.DMRESERVED1);
}
