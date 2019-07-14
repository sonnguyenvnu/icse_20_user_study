static void localFoldInHasContainer(final HasStepFolder janusgraphStep,final Step<?,?> tinkerpopStep,final Traversal.Admin<?,?> traversal,final Traversal<?,?> rootTraversal){
  Step<?,?> currentStep=tinkerpopStep;
  while (true) {
    if (currentStep instanceof HasContainerHolder) {
      final Iterable<HasContainer> containers=((HasContainerHolder)currentStep).getHasContainers().stream().map(c -> JanusGraphPredicate.Converter.convert(c)).collect(Collectors.toList());
      final List<HasContainer> hasContainers=janusgraphStep.addLocalAll(containers);
      currentStep.getLabels().forEach(janusgraphStep::addLabel);
      traversal.removeStep(currentStep);
      currentStep=foldInOrder(janusgraphStep,currentStep,traversal,rootTraversal,janusgraphStep instanceof JanusGraphStep && ((JanusGraphStep)janusgraphStep).returnsVertex(),hasContainers);
      foldInRange(janusgraphStep,currentStep,traversal,hasContainers);
    }
 else     if (!(currentStep instanceof IdentityStep) && !(currentStep instanceof NoOpBarrierStep)) {
      break;
    }
    currentStep=currentStep.getNextStep();
  }
}
