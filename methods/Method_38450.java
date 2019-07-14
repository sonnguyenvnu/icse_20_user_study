/** 
 * Creates  {@link HtmlStaplerBundlesManager} instance.
 */
protected HtmlStaplerBundlesManager createBundleManager(final ServletContext servletContext,final Strategy strategy){
  String webRoot=servletContext.getRealPath(StringPool.EMPTY);
  String contextPath=ServletUtil.getContextPath(servletContext);
  return new HtmlStaplerBundlesManager(contextPath,webRoot,strategy);
}
