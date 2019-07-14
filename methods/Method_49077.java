static void foldInIds(final HasStepFolder janusgraphStep,final Traversal.Admin<?,?> traversal){
  Step<?,?> currentStep=janusgraphStep.getNextStep();
  while (true) {
    if (currentStep instanceof HasContainerHolder) {
      final HasContainerHolder hasContainerHolder=(HasContainerHolder)currentStep;
      final GraphStep graphStep=(GraphStep)janusgraphStep;
      final List<HasContainer> removableHasContainers=new ArrayList<>();
      final Set<String> stepLabels=currentStep.getLabels();
      hasContainerHolder.getHasContainers().forEach(hasContainer -> {
        if (GraphStep.processHasContainerIds(graphStep,hasContainer)) {
          stepLabels.forEach(janusgraphStep::addLabel);
          removableHasContainers.add(hasContainer);
        }
      }
);
      if (!removableHasContainers.isEmpty()) {
        removableHasContainers.forEach(hasContainerHolder::removeHasContainer);
      }
      if (hasContainerHolder.getHasContainers().isEmpty()) {
        traversal.removeStep(currentStep);
      }
    }
 else     if (currentStep instanceof IdentityStep) {
    }
 else     if (currentStep instanceof NoOpBarrierStep) {
    }
 else {
      break;
    }
    currentStep=currentStep.getNextStep();
  }
}
