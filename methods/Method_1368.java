/** 
 * Check if uri represents network resource
 * @param uri uri to check
 * @return true if uri's scheme is equal to "http" or "https"
 */
public static boolean isNetworkUri(@Nullable Uri uri){
  final String scheme=getSchemeOrNull(uri);
  return HTTPS_SCHEME.equals(scheme) || HTTP_SCHEME.equals(scheme);
}
