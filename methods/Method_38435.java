/** 
 * Lookups for bundle file.
 */
public File lookupBundleFile(String bundleId){
  if ((mirrors != null) && (!mirrors.isEmpty())) {
    String realBundleId=mirrors.remove(bundleId);
    if (realBundleId != null) {
      bundleId=realBundleId;
    }
  }
  return createBundleFile(bundleId);
}
