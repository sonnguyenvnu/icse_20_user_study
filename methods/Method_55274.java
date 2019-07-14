/** 
 * Unsafe version of  {@link #dmDriverVersion}. 
 */
public static short ndmDriverVersion(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMDRIVERVERSION);
}
