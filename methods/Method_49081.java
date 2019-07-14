static Step<?,?> foldInOrder(final HasStepFolder janusgraphStep,final Step<?,?> tinkerpopStep,final Traversal.Admin<?,?> traversal,final Traversal<?,?> rootTraversal,boolean isVertexOrder,final List<HasContainer> hasContainers){
  Step<?,?> currentStep=tinkerpopStep;
  OrderGlobalStep<?,?> lastOrder=null;
  while (true) {
    if (currentStep instanceof OrderGlobalStep) {
      if (lastOrder != null) {
        lastOrder.getLabels().forEach(janusgraphStep::addLabel);
        traversal.removeStep(lastOrder);
      }
      lastOrder=(OrderGlobalStep)currentStep;
    }
 else     if (!(currentStep instanceof IdentityStep) && !(currentStep instanceof HasStep) && !(currentStep instanceof NoOpBarrierStep)) {
      break;
    }
    currentStep=currentStep.getNextStep();
  }
  if (lastOrder != null && validJanusGraphOrder(lastOrder,rootTraversal,isVertexOrder)) {
    for (    final Pair<Traversal.Admin<Object,Comparable>,Comparator<Object>> comp : (List<Pair<Traversal.Admin<Object,Comparable>,Comparator<Object>>>)((OrderGlobalStep)lastOrder).getComparators()) {
      final String key;
      final Order order;
      if (comp.getValue0() instanceof ElementValueTraversal) {
        final ElementValueTraversal evt=(ElementValueTraversal)comp.getValue0();
        key=evt.getPropertyKey();
        order=(Order)comp.getValue1();
      }
 else {
        final ElementValueComparator evc=(ElementValueComparator)comp.getValue1();
        key=evc.getPropertyKey();
        order=(Order)evc.getValueComparator();
      }
      if (hasContainers == null) {
        janusgraphStep.orderBy(key,order);
      }
 else {
        janusgraphStep.localOrderBy(hasContainers,key,order);
      }
    }
    lastOrder.getLabels().forEach(janusgraphStep::addLabel);
    traversal.removeStep(lastOrder);
  }
  return currentStep;
}
