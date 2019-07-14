@Override protected void validateTestMethods(List<Throwable> errors){
  for (  FrameworkMethod each : computeTestMethods()) {
    if (each.getAnnotation(Theory.class) != null) {
      each.validatePublicVoid(false,errors);
      each.validateNoTypeParametersOnArgs(errors);
    }
 else {
      each.validatePublicVoidNoArg(false,errors);
    }
    for (    ParameterSignature signature : ParameterSignature.signatures(each.getMethod())) {
      ParametersSuppliedBy annotation=signature.findDeepAnnotation(ParametersSuppliedBy.class);
      if (annotation != null) {
        validateParameterSupplier(annotation.value(),errors);
      }
    }
  }
}
