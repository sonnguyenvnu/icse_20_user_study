private static boolean isChildOf(Step<?,?> currentStep,List<Class<? extends Step>> stepClasses){
  Step<?,?> parent=currentStep.getTraversal().getParent().asStep();
  while (!parent.equals(EmptyStep.instance())) {
    final Step<?,?> p=parent;
    if (stepClasses.stream().filter(stepClass -> stepClass.isInstance(p)).findFirst().isPresent()) {
      return true;
    }
    parent=parent.getTraversal().getParent().asStep();
  }
  return false;
}
