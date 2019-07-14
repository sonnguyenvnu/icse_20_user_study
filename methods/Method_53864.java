/** 
 * Unsafe version of  {@link #mFileExtensionsString}. 
 */
public static String nmFileExtensionsString(long struct){
  return memASCII(memGetAddress(struct + AIImporterDesc.MFILEEXTENSIONS));
}
