/** 
 * Lookups for a bundle id for a given action. Returns <code>null</code> if action still has no bundle. Returns an empty string if action has an empty bundle.
 */
public String lookupBundleId(final String actionPath){
  return actionBundles.get(actionPath);
}
