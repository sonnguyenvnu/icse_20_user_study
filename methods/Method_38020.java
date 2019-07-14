/** 
 * Removes surrounding prefix and suffixes.
 */
public static String cutSurrounding(final String string,final String prefix,final String suffix){
  int start=0;
  int end=string.length();
  if (string.startsWith(prefix)) {
    start=prefix.length();
  }
  if (string.endsWith(suffix)) {
    end-=suffix.length();
  }
  if (end <= start) {
    return StringPool.EMPTY;
  }
  return string.substring(start,end);
}
