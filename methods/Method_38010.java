/** 
 * Converts string charset. If charset names are the same, the same string is returned.
 */
public static String convertCharset(final String source,final String srcCharsetName,final String newCharsetName){
  if (srcCharsetName.equals(newCharsetName)) {
    return source;
  }
  return StringUtil.newString(StringUtil.getBytes(source,srcCharsetName),newCharsetName);
}
