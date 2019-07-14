static void foldInHasContainer(final HasStepFolder janusgraphStep,final Traversal.Admin<?,?> traversal,final Traversal<?,?> rootTraversal){
  Step<?,?> currentStep=janusgraphStep.getNextStep();
  while (true) {
    if (currentStep instanceof OrStep && janusgraphStep instanceof JanusGraphStep) {
      for (      final Traversal.Admin<?,?> child : ((OrStep<?>)currentStep).getLocalChildren()) {
        if (!validFoldInHasContainer(child.getStartStep(),false)) {
          return;
        }
      }
      ((OrStep<?>)currentStep).getLocalChildren().forEach(t -> localFoldInHasContainer(janusgraphStep,t.getStartStep(),t,rootTraversal));
      traversal.removeStep(currentStep);
    }
 else     if (currentStep instanceof HasContainerHolder) {
      final Iterable<HasContainer> containers=((HasContainerHolder)currentStep).getHasContainers().stream().map(c -> JanusGraphPredicate.Converter.convert(c)).collect(Collectors.toList());
      if (validFoldInHasContainer(currentStep,true)) {
        janusgraphStep.addAll(containers);
        currentStep.getLabels().forEach(janusgraphStep::addLabel);
        traversal.removeStep(currentStep);
      }
    }
 else     if (!(currentStep instanceof IdentityStep) && !(currentStep instanceof NoOpBarrierStep) && !(currentStep instanceof HasContainerHolder)) {
      break;
    }
    currentStep=currentStep.getNextStep();
  }
}
