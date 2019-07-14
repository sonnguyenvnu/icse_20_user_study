/** 
 * Returns the value stored under  {@link #KEY_REDIRECTED_URI} as a {@link Uri}, or {code null} if not set.
 */
@Nullable static Uri getRedirectedUri(ContentMetadata contentMetadata){
  String redirectedUri=contentMetadata.get(KEY_REDIRECTED_URI,(String)null);
  return redirectedUri == null ? null : Uri.parse(redirectedUri);
}
