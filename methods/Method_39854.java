/** 
 * @see #checkCsrfToken(javax.servlet.http.HttpServletRequest,String) 
 */
public static boolean checkCsrfToken(final HttpServletRequest request){
  return checkCsrfToken(request,CSRF_TOKEN_NAME);
}
