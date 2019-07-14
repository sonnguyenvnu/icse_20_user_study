private static void getMultiQueryCompatibleStepsFromChildTraversal(Traversal.Admin<?,?> childTraversal,Step parentStep,Set<Step> multiQueryCompatibleSteps){
  Step firstStep=childTraversal.getStartStep();
  while (firstStep instanceof StartStep || firstStep instanceof SideEffectStep) {
    firstStep=firstStep.getNextStep();
  }
  if (firstStep.getClass().isAssignableFrom(VertexStep.class)) {
    multiQueryCompatibleSteps.add(parentStep);
  }
}
