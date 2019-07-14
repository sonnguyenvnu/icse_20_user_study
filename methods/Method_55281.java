/** 
 * Unsafe version of  {@link #dmCopies}. 
 */
public static short ndmCopies(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMCOPIES);
}
