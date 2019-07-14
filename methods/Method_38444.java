/** 
 * Resolves real action path for given one. When some URLs are dynamically created, many different links points to the same page. Use this to prevent memory leaking.
 */
protected String resolveRealActionPath(final String actionPath){
  return actionPath;
}
