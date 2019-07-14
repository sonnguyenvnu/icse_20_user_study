/** 
 * Creates  {@link WebApp}.
 */
protected WebApp createWebApplication(){
  if ((webAppClassName == null) && (webAppClass == null)) {
    return new WebApp();
  }
  final WebApp webApp;
  try {
    if (webAppClassName != null) {
      webAppClass=ClassLoaderUtil.loadClass(webAppClassName);
    }
    webApp=(WebApp)ClassUtil.newInstance(webAppClass);
  }
 catch (  Exception ex) {
    throw new MadvocException("Unable to load Madvoc web application class: " + webAppClassName,ex);
  }
  return webApp;
}
