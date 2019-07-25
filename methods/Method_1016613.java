/** 
 * Add bundle to collection.
 * @throws BundleException 
 */
void add(Bundle bundle) throws BundleException {
  Bundle existingBundle=symbolicName2bundle.put(bundle.getSymbolicName(),bundle);
  if (null != existingBundle) {
    throw new BundleException(String.format("Bundle '%s' (ID: %d) is already part of collection with ID %d.",bundle.getSymbolicName(),bundle.getBundleId(),existingBundle.getBundleId()),BundleException.DUPLICATE_BUNDLE_ERROR);
  }
  Bundle bundleWithSameID=id2bundle.put(bundle.getBundleId(),bundle);
  if (null != bundleWithSameID) {
    throw new BundleException(String.format("Bundle ID '%d' for '%s' is already used by '%s'.",bundle.getBundleId(),bundle.getSymbolicName(),bundleWithSameID.getSymbolicName()),BundleException.DUPLICATE_BUNDLE_ERROR);
  }
}
