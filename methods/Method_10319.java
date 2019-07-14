/** 
 * Non-standard helper method, to delete cookie
 * @param cookie cookie to be removed
 */
public void deleteCookie(Cookie cookie){
  String name=cookie.getName() + cookie.getDomain();
  cookies.remove(name);
  SharedPreferences.Editor prefsWriter=cookiePrefs.edit();
  prefsWriter.remove(COOKIE_NAME_PREFIX + name);
  prefsWriter.apply();
}
