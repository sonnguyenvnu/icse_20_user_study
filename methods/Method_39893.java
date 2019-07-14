/** 
 * Stores context path in server context and request scope.
 */
public static void storeContextPath(final PageContext pageContext,final String contextPathVariableName){
  String ctxPath=getContextPath(pageContext);
  HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
  request.setAttribute(contextPathVariableName,ctxPath);
  ServletContext servletContext=pageContext.getServletContext();
  servletContext.setAttribute(contextPathVariableName,ctxPath);
}
