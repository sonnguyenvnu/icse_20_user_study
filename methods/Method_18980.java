@Nullable private static SpecMethodModel<EventMethod,WorkingRangeDeclarationModel> generateWorkingRangeMethod(Elements elements,ExecutableElement executableElement,List<Class<? extends Annotation>> permittedInterStageInputAnnotations,Messager messager,Class<? extends Annotation> annotationType){
  final List<MethodParamModel> methodParams=getMethodParams(executableElement,messager,getPermittedMethodParamAnnotations(permittedInterStageInputAnnotations),permittedInterStageInputAnnotations,ImmutableList.of());
  final String nameInAnnotation=ProcessorUtils.getAnnotationParameter(elements,executableElement,annotationType,"name",String.class);
  List<? extends AnnotationMirror> annotationMirrors=executableElement.getAnnotationMirrors();
  AnnotationMirror mirror=null;
  for (  AnnotationMirror m : annotationMirrors) {
    if (m.getAnnotationType().toString().equals(annotationType.getCanonicalName())) {
      mirror=m;
      break;
    }
  }
  return SpecMethodModel.<EventMethod,WorkingRangeDeclarationModel>builder().annotations(ImmutableList.of()).modifiers(ImmutableList.copyOf(new ArrayList<>(executableElement.getModifiers()))).name(executableElement.getSimpleName()).returnTypeSpec(generateTypeSpec(executableElement.getReturnType())).typeVariables(ImmutableList.copyOf(getTypeVariables(executableElement))).methodParams(ImmutableList.copyOf(methodParams)).representedObject(executableElement).typeModel(new WorkingRangeDeclarationModel(nameInAnnotation,mirror)).build();
}
