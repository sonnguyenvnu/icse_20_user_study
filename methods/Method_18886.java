public static boolean isLazyStateParam(MethodParamModel methodParamModel){
  return methodParamModel instanceof StateParamModel && ((StateParamModel)methodParamModel).canUpdateLazily();
}
