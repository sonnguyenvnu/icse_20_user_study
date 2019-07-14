/** 
 * Finds all extensions that belong to given mime type(s). If wildcard mode is on, provided mime type is wildcard pattern.
 * @param mimeType list of mime types, separated by comma
 * @param useWildcard if set, mime types are wildcard patterns
 */
public static String[] findExtensionsByMimeTypes(String mimeType,final boolean useWildcard){
  final ArrayList<String> extensions=new ArrayList<>();
  mimeType=mimeType.toLowerCase();
  final String[] mimeTypes=StringUtil.splitc(mimeType,", ");
  for (  final Map.Entry<String,String> entry : MIME_TYPE_MAP.entrySet()) {
    final String entryExtension=entry.getKey();
    final String entryMimeType=entry.getValue().toLowerCase();
    final int matchResult=useWildcard ? Wildcard.matchOne(entryMimeType,mimeTypes) : StringUtil.equalsOne(entryMimeType,mimeTypes);
    if (matchResult != -1) {
      extensions.add(entryExtension);
    }
  }
  if (extensions.isEmpty()) {
    return StringPool.EMPTY_ARRAY;
  }
  return extensions.toArray(new String[0]);
}
