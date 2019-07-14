/** 
 * Unsafe version of  {@link #fileExtensionString}. 
 */
public static String nfileExtensionString(long struct){
  return memUTF8(memGetAddress(struct + AIExportFormatDesc.FILEEXTENSION));
}
