/** 
 * Check if uri represents local content
 * @param uri uri to check
 * @return true if uri's scheme is equal to "content"
 */
public static boolean isLocalContentUri(@Nullable Uri uri){
  final String scheme=getSchemeOrNull(uri);
  return LOCAL_CONTENT_SCHEME.equals(scheme);
}
