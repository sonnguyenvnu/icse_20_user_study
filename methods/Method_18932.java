public static List<SpecMethodModel<DelegateMethod,Void>> getMethodModelsWithAnnotation(SpecModel specModel,Class<? extends Annotation> annotationClass){
  final List<SpecMethodModel<DelegateMethod,Void>> methodModels=new ArrayList<>();
  for (  SpecMethodModel<DelegateMethod,Void> delegateMethodModel : specModel.getDelegateMethods()) {
    for (    Annotation annotation : delegateMethodModel.annotations) {
      if (annotation.annotationType().equals(annotationClass)) {
        methodModels.add(delegateMethodModel);
      }
    }
  }
  return methodModels;
}
