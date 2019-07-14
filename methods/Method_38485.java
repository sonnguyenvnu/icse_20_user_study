/** 
 * Reads cookies from response and adds to cookies list.
 */
protected void readCookies(final HttpResponse httpResponse){
  Cookie[] newCookies=httpResponse.cookies();
  for (  Cookie cookie : newCookies) {
    cookies.add(cookie.getName(),cookie);
  }
}
