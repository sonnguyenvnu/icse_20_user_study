/** 
 * Registers new, temporary bundle id for given action path. This id is used on first bundle usage, later it will be replaces with real bundle id.
 */
public String registerNewBundleId(){
  bundleCount++;
  return String.valueOf(bundleCount);
}
