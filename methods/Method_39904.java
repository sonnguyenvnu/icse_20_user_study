/** 
 * Returns servlet error.
 */
public static Throwable getServletError(final ServletRequest request){
  return (Throwable)request.getAttribute(JAVAX_SERVLET_ERROR_EXCEPTION);
}
