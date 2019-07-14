/** 
 * Unsafe version of  {@link #mAuthorString}. 
 */
public static String nmAuthorString(long struct){
  return memUTF8(memGetAddress(struct + AIImporterDesc.MAUTHOR));
}
