/** 
 * Unsafe version of  {@link #dmReserved2}. 
 */
public static int ndmReserved2(long struct){
  return UNSAFE.getInt(null,struct + DEVMODE.DMRESERVED2);
}
