/** 
 * @param janusgraphStep The step to test
 * @return True if there are 'has' steps following this step and no subsequent range limit step
 */
static boolean foldableHasContainerNoLimit(FlatMapStep<?,?> janusgraphStep){
  boolean foldableHasContainerNoLimit=false;
  Step<?,?> currentStep=janusgraphStep.getNextStep();
  while (true) {
    if (currentStep instanceof OrStep) {
      for (      final Traversal.Admin<?,?> child : ((OrStep<?>)currentStep).getLocalChildren()) {
        if (!validFoldInHasContainer(child.getStartStep(),false)) {
          return false;
        }
      }
      foldableHasContainerNoLimit=true;
    }
 else     if (currentStep instanceof HasContainerHolder) {
      if (validFoldInHasContainer(currentStep,true)) {
        foldableHasContainerNoLimit=true;
      }
    }
 else     if (currentStep instanceof RangeGlobalStep) {
      return false;
    }
 else     if (!(currentStep instanceof IdentityStep) && !(currentStep instanceof NoOpBarrierStep)) {
      break;
    }
    currentStep=currentStep.getNextStep();
  }
  return foldableHasContainerNoLimit;
}
