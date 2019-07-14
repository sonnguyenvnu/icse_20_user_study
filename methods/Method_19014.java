/** 
 * Generates a list of  {@link ChangeSet} for the children of newRoot and currentRoot. Thegenerated SparseArray will contain an element for each children of currentRoot.  {@link ChangeSet}s for new items in newRoot will me merged in place with the appropriate  {@link ChangeSet}. If for example a new child is added in position 2, its  {@link ChangeSet} will bemerged with the  {@link ChangeSet} generated for the child of currentRoot in position 1. Thisstill guarantees a correct ordering while preserving the validity of indexes in the children of currentRoot. Re-ordering a child is not supported and will trigger an  {@link IllegalStateException}.
 */
private static SparseArray<ChangeSet> generateChildrenChangeSets(SectionContext sectionContext,Map<String,Pair<Section,Integer>> currentChildren,Map<String,Pair<Section,Integer>> newChildren,List<Section> currentChildrenList,List<Section> newChildrenList,List<Section> removedComponents,SectionsDebugLogger sectionsDebugLogger,String sectionTreeTag,String currentPrefix,String newPrefix,String thread,boolean enableStats){
  final SparseArray<ChangeSet> changeSets=acquireChangeSetSparseArray();
  for (int i=0; i < currentChildrenList.size(); i++) {
    final String key=currentChildrenList.get(i).getGlobalKey();
    final Section currentChild=currentChildrenList.get(i);
    if (newChildren.get(key) == null) {
      changeSets.put(i,generateChangeSetRecursive(sectionContext,currentChild,null,removedComponents,sectionsDebugLogger,sectionTreeTag,currentPrefix,newPrefix,thread,enableStats));
    }
  }
  int activeChildIndex=0;
  for (int i=0; i < newChildrenList.size(); i++) {
    final Section newChild=newChildrenList.get(i);
    final Pair<Section,Integer> valueAndPosition=currentChildren.get(newChild.getGlobalKey());
    final int currentChildIndex=valueAndPosition != null ? valueAndPosition.second : -1;
    if (currentChildIndex < 0) {
      final ChangeSet currentChangeSet=changeSets.get(activeChildIndex);
      final ChangeSet changeSet=generateChangeSetRecursive(sectionContext,null,newChild,removedComponents,sectionsDebugLogger,sectionTreeTag,currentPrefix,newPrefix,thread,enableStats);
      changeSets.put(activeChildIndex,ChangeSet.merge(currentChangeSet,changeSet));
      if (currentChangeSet != null) {
        currentChangeSet.release();
      }
      changeSet.release();
    }
 else {
      activeChildIndex=currentChildIndex;
      final ChangeSet currentChangeSet=changeSets.get(activeChildIndex);
      final ChangeSet changeSet=generateChangeSetRecursive(sectionContext,currentChildrenList.get(currentChildIndex),newChild,removedComponents,sectionsDebugLogger,sectionTreeTag,currentPrefix,newPrefix,thread,enableStats);
      changeSets.put(activeChildIndex,ChangeSet.merge(currentChangeSet,changeSet));
      if (currentChangeSet != null) {
        currentChangeSet.release();
      }
      changeSet.release();
    }
  }
  releaseChildrenMap(currentChildren);
  releaseChildrenMap(newChildren);
  return changeSets;
}
