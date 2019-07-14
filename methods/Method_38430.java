/** 
 * Builds stapler URL based on bundle action data.
 */
protected String buildStaplerUrl(){
  return contextPath + '/' + bundlesManager.getStaplerPath() + '/' + bundleId;
}
