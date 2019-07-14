public static boolean hasLazyState(SpecModel specModel){
  for (  StateParamModel stateParamModel : specModel.getStateValues()) {
    if (stateParamModel.canUpdateLazily()) {
      return true;
    }
  }
  return false;
}
