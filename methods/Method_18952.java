static List<Class<? extends Annotation>> getPermittedMethodParamAnnotations(List<Class<? extends Annotation>> permittedInterStageInputAnnotations){
  final List<Class<? extends Annotation>> permittedMethodParamAnnotations=new ArrayList<>(METHOD_PARAM_ANNOTATIONS);
  permittedMethodParamAnnotations.addAll(permittedInterStageInputAnnotations);
  return permittedMethodParamAnnotations;
}
