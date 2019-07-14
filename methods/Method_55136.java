/** 
 * Locates a bundle given its program-defined identifier.
 * @param bundleID the identifier of the bundle to locate. Note that identifier names are case-sensitive.
 */
@NativeType("CFBundleRef") public static long CFBundleGetBundleWithIdentifier(@NativeType("CFStringRef") long bundleID){
  if (CHECKS) {
    check(bundleID);
  }
  return nCFBundleGetBundleWithIdentifier(bundleID);
}
