static boolean validFoldInHasContainer(final Step<?,?> tinkerpopStep,final boolean defaultValue){
  Step<?,?> currentStep=tinkerpopStep;
  Boolean toReturn=null;
  while (!(currentStep instanceof EmptyStep)) {
    if (currentStep instanceof HasContainerHolder) {
      final Iterable<HasContainer> containers=((HasContainerHolder)currentStep).getHasContainers();
      toReturn=toReturn == null ? validJanusGraphHas(containers) : toReturn && validJanusGraphHas(containers);
    }
 else     if (!(currentStep instanceof IdentityStep) && !(currentStep instanceof NoOpBarrierStep) && !(currentStep instanceof RangeGlobalStep) && !(currentStep instanceof OrderGlobalStep)) {
      toReturn=toReturn == null ? false : (toReturn && defaultValue);
      break;
    }
    currentStep=currentStep.getNextStep();
  }
  return Boolean.TRUE.equals(toReturn);
}
