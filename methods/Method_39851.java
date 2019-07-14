/** 
 * @see #prepareCsrfToken(javax.servlet.http.HttpSession,int)
 */
public static String prepareCsrfToken(final HttpSession session){
  return prepareCsrfToken(session,timeToLive);
}
