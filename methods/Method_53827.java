/** 
 * Unsafe version of  {@link #idString}. 
 */
public static String nidString(long struct){
  return memUTF8(memGetAddress(struct + AIExportFormatDesc.ID));
}
