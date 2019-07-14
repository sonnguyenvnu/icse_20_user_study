/** 
 * Checks if  {@link jodd.servlet.tag.CsrfTokenTag CSRF token} is valid.Returns <code>false</code> if token was requested, but not found. Otherwise, it returns <code>true</code>.
 */
@SuppressWarnings({"unchecked"}) public static boolean checkCsrfToken(final HttpServletRequest request,final String tokenName){
  String tokenValue=request.getParameter(tokenName);
  return checkCsrfToken(request.getSession(),tokenValue);
}
