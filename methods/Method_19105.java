/** 
 * Children of a GroupSectionSpec section will be other DebugSections. Children of a DiffSectionSpec will be views.
 */
public List<?> getSectionChildren(){
  if (mSectionDebugNode.isDiffSectionSpec()) {
    final List<View> childViews=new ArrayList<>();
    for (int i=0; i < mViews.size(); i++) {
      final View childView=mViews.get(i);
      final Section renderInfoSection=(Section)RenderInfoDebugInfoRegistry.getRenderInfoSectionDebugInfo(childView);
      if (renderInfoSection != null && renderInfoSection.getGlobalKey().equals(mSectionDebugNode.getGlobalKey())) {
        childViews.add(childView);
      }
    }
    return childViews;
  }
 else {
    final List<DebugSection> childrenDebugSections=new ArrayList<>();
    for (    Section child : mSectionDebugNode.getChildren()) {
      final DebugSection debugSection=new DebugSection(child,mViews);
      if (debugSection.getSectionChildren().size() > 0) {
        childrenDebugSections.add(debugSection);
      }
    }
    return childrenDebugSections;
  }
}
