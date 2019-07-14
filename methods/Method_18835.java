private static boolean hasStatesThatCanUpdateLazily(SpecModel specModel){
  for (  StateParamModel stateValue : specModel.getStateValues()) {
    if (stateValue.canUpdateLazily()) {
      return true;
    }
  }
  return false;
}
