/** 
 * Calculates the global starting index for each section in the hierarchy. 
 */
@UiThread private SectionLocationInfo findSectionForKey(String key){
  if (mBoundSection == null) {
    throw new IllegalStateException("You cannot call requestFocus methods before dataBound() is called!");
  }
  final SectionLocationInfo sectionLocationInfo=findSectionForKeyRecursive(mBoundSection,key,0);
  if (sectionLocationInfo == null) {
    throw new SectionKeyNotFoundException("Did not find section with key '" + key + "'!");
  }
  return sectionLocationInfo;
}
