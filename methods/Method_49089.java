private static void unfoldLocalTraversal(final Traversal.Admin<?,?> traversal,LocalStep<?,?> localStep,Traversal.Admin localTraversal,MultiQueriable vertexStep,boolean useMultiQuery){
  assert localTraversal.asAdmin().getSteps().size() > 0;
  if (localTraversal.asAdmin().getSteps().size() == 1) {
    assert localTraversal.getStartStep() == vertexStep;
    vertexStep.setTraversal(traversal);
    TraversalHelper.replaceStep(localStep,vertexStep,traversal);
    if (useMultiQuery) {
      vertexStep.setUseMultiQuery(true);
    }
  }
}
