/** 
 * Stores the ref tag in a cookie and shared preference for the project.
 */
public static void storeCookie(final @NonNull RefTag refTag,final @NonNull Project project,final @NonNull CookieManager cookieManager,final @NonNull SharedPreferences sharedPreferences){
  final HttpCookie cookie=buildCookieWithRefTagAndProject(refTag,project);
  cookieManager.getCookieStore().add(null,cookie);
  if (cookie != null) {
    final SharedPreferences.Editor editor=sharedPreferences.edit();
    editor.putString(cookie.getName(),cookie.getValue());
    editor.apply();
  }
}
