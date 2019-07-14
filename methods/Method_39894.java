/** 
 * Stores context path in page context and request scope.
 */
public static void storeContextPath(final ServletContext servletContext,final String contextPathVariableName){
  String ctxPath=getContextPath(servletContext);
  servletContext.setAttribute(contextPathVariableName,ctxPath);
}
