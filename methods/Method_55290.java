/** 
 * Unsafe version of  {@link #dmFormNameString}. 
 */
public static String ndmFormNameString(long struct){
  return memUTF16(struct + DEVMODE.DMFORMNAME);
}
