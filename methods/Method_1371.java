/** 
 * Check if uri represents fully qualified resource URI.
 * @param uri uri to check
 * @return true if uri's scheme is equal to {@link #QUALIFIED_RESOURCE_SCHEME}
 */
public static boolean isQualifiedResourceUri(@Nullable Uri uri){
  final String scheme=getSchemeOrNull(uri);
  return QUALIFIED_RESOURCE_SCHEME.equals(scheme);
}
