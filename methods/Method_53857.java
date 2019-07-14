/** 
 * Unsafe version of  {@link #mCommentsString}. 
 */
public static String nmCommentsString(long struct){
  return memUTF8(memGetAddress(struct + AIImporterDesc.MCOMMENTS));
}
