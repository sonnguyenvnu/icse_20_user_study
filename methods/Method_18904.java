public static boolean hasLazyStateParams(SpecMethodModel<?,?> specMethodModel){
  for (  MethodParamModel stateParamModel : specMethodModel.methodParams) {
    if (MethodParamModelUtils.isLazyStateParam(stateParamModel)) {
      return true;
    }
  }
  return false;
}
