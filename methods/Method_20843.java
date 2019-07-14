/** 
 * If a ref tag cookie has been stored for this project this returns the ref tag embedded in the cookie. If a cookie has not yet been set it returns `null`.
 */
public static @Nullable RefTag storedCookieRefTagForProject(final @NonNull Project project,final @NonNull CookieManager cookieManager,final @NonNull SharedPreferences sharedPreferences){
  final HttpCookie cookie=findRefTagCookieForProject(project,cookieManager,sharedPreferences);
  if (cookie == null) {
    return null;
  }
  final String[] components=cookie.getValue().split(COOKIE_VALUE_SEPARATOR);
  if (components.length > 0) {
    return RefTag.from(components[0]);
  }
  return null;
}
