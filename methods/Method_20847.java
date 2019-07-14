/** 
 * Finds the ref tag cookie associated with a project. Returns `null` if no cookie has yet been set.
 */
protected static @Nullable HttpCookie findRefTagCookieForProject(final @NonNull Project project,final @NonNull CookieManager cookieManager,final @NonNull SharedPreferences sharedPreferences){
  final String cookieName=cookieNameForProject(project);
  final CookieStore cookieStore=cookieManager.getCookieStore();
  for (  final HttpCookie cookie : cookieStore.getCookies()) {
    if (cookieName.equals(cookie.getName())) {
      return cookie;
    }
  }
  final String cookieValue=sharedPreferences.getString(cookieName,null);
  if (cookieValue != null) {
    return buildCookieWithValueAndProject(cookieValue,project);
  }
  return null;
}
