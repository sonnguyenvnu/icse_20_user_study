/** 
 * Extract the project param from a uri. e.g.: A uri like `ksr://www.kickstarter.com/projects/1186238668/skull-graphic-tee` returns `skull-graphic-tee`.
 */
private static @Nullable String paramFromUri(final @Nullable Uri uri){
  if (uri == null) {
    return null;
  }
  final String scheme=uri.getScheme();
  if (!(scheme.equals(SCHEME_KSR) || scheme.equals(SCHEME_HTTPS))) {
    return null;
  }
  final Matcher matcher=PROJECT_PATTERN.matcher(uri.getPath());
  if (matcher.matches() && matcher.group(3) != null) {
    return matcher.group(3);
  }
  return null;
}
