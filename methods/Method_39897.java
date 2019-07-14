/** 
 * Returns <code>true</code> if current URL is absolute, <code>false</code> otherwise.
 */
public static boolean isAbsoluteUrl(final String url){
  if (url == null) {
    return false;
  }
  int colonPos;
  if ((colonPos=url.indexOf(':')) == -1) {
    return false;
  }
  for (int i=0; i < colonPos; i++) {
    if (VALID_SCHEME_CHARS.indexOf(url.charAt(i)) == -1) {
      return false;
    }
  }
  return true;
}
