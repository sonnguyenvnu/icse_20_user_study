static List<MethodParamModel> getMethodParams(PsiMethod method,List<Class<? extends Annotation>> permittedAnnotations,List<Class<? extends Annotation>> permittedInterStageInputAnnotations,List<Class<? extends Annotation>> delegateMethodAnnotationsThatSkipDiffModels){
  final List<MethodParamModel> methodParamModels=new ArrayList<>();
  PsiParameter[] params=method.getParameterList().getParameters();
  for (  final PsiParameter param : params) {
    methodParamModels.add(MethodParamModelFactory.create(PsiTypeUtils.generateTypeSpec(param.getType()),param.getName(),getLibraryAnnotations(param,permittedAnnotations),getExternalAnnotations(param),permittedInterStageInputAnnotations,canCreateDiffModels(method,delegateMethodAnnotationsThatSkipDiffModels),param));
  }
  return methodParamModels;
}
