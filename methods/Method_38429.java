/** 
 * Process links. Returns bundle link if this is the first resource of the same type. Otherwise, returns <code>null</code> indicating that collection is going on and the original link should be removed.
 */
public String processLink(final String src){
  if (newAction) {
    if (bundleId == null) {
      bundleId=bundlesManager.registerNewBundleId();
      bundleId+='.' + bundleContentType;
    }
    sources.add(src);
  }
  if (firstScriptTag) {
    firstScriptTag=false;
    return buildStaplerUrl();
  }
 else {
    return null;
  }
}
