private static boolean hasSameUnderlyingStateParamModel(Set<StateParamModel> props,DiffStateParamModel diffStateParamModel){
  for (  StateParamModel existingStateParamModel : props) {
    if (diffStateParamModel.isSameUnderlyingStateValueModel(existingStateParamModel)) {
      return true;
    }
  }
  return false;
}
