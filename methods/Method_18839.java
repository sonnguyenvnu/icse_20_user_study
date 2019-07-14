static boolean hasUpdateStateWithTransition(SpecModel specModel){
  return specModel.getUpdateStateWithTransitionMethods() != null && !specModel.getUpdateStateWithTransitionMethods().isEmpty();
}
