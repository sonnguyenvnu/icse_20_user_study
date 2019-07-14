/** 
 * Sets cookies to the request.
 */
public HttpRequest cookies(final Cookie... cookies){
  if (cookies.length == 0) {
    return this;
  }
  StringBuilder cookieString=new StringBuilder();
  boolean first=true;
  for (  Cookie cookie : cookies) {
    Integer maxAge=cookie.getMaxAge();
    if (maxAge != null && maxAge.intValue() == 0) {
      continue;
    }
    if (!first) {
      cookieString.append("; ");
    }
    first=false;
    cookieString.append(cookie.getName());
    cookieString.append('=');
    cookieString.append(cookie.getValue());
  }
  headerOverwrite("cookie",cookieString.toString());
  return this;
}
