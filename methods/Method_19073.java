/** 
 * Calculates the global starting index for each section in the hierarchy. 
 */
@Nullable @UiThread private SectionLocationInfo findSectionForKeyRecursive(@Nullable Section root,String key,int prevChildrenCount){
  if (root == null) {
    return null;
  }
  if (key.equals(root.getGlobalKey())) {
    return new SectionLocationInfo(root,prevChildrenCount);
  }
  final List<Section> children=root.getChildren();
  if (children == null || children.isEmpty()) {
    return null;
  }
  int currentChildrenCount=0;
  for (int i=0, size=children.size(); i < size; i++) {
    final Section child=children.get(i);
    final SectionLocationInfo result=findSectionForKeyRecursive(child,key,prevChildrenCount + currentChildrenCount);
    if (result != null) {
      return result;
    }
    currentChildrenCount+=child.getCount();
  }
  return null;
}
