/** 
 * Accepts action path for further parsing. By default, only <code>*.htm(l)</code> requests are passed through and those without any extension.
 */
protected boolean acceptActionPath(final HttpServletRequest request,final String actionPath){
  String extension=FileNameUtil.getExtension(actionPath);
  if (extension.length() == 0) {
    return true;
  }
  if (extension.equals("html") || extension.equals("htm")) {
    return true;
  }
  return false;
}
