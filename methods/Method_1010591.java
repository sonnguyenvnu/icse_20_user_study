/** 
 * Iterate over given nodes. IMPORTANT! This method doesn't look into children of supplied nodes, it takes parameter 'literally' and visits only nodes specified. To visit complete tree, consider using  {@link SNodeUtil#getDescendants(SNode)}.
 * @return <code>this</code> for convenience
 */
public ModelDependencyScanner walk(@NotNull Iterable<org.jetbrains.mps.openapi.model.SNode> nodes){
  HashSet<org.jetbrains.mps.openapi.model.SModelReference> allRefTargets=new HashSet<>();
  HashSet<org.jetbrains.mps.openapi.model.SModelReference> sourceModels=new HashSet<>();
  for (  SNode n : nodes) {
    if (myNeedConcepts) {
      myConcepts.add(n.getConcept());
    }
    if (myNeedLanguages) {
      myUsedLanguages.add(n.getConcept().getLanguage());
    }
    if (myNeedCrossModel) {
      final org.jetbrains.mps.openapi.model.SModel sourceModel=n.getModel();
      if (sourceModel != null) {
        sourceModels.add(sourceModel.getReference());
      }
      for (      SReference ref : n.getReferences()) {
        final org.jetbrains.mps.openapi.model.SModelReference targetModel=ref.getTargetSModelReference();
        if (targetModel != null) {
          allRefTargets.add(targetModel);
        }
      }
    }
  }
  if (myNeedCrossModel) {
    allRefTargets.removeAll(sourceModels);
    myCrossModelReferences.addAll(allRefTargets);
  }
  return this;
}
