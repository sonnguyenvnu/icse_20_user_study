/** 
 * @return a list of params for a method. 
 */
static List<MethodParamModel> getMethodParams(ExecutableElement method,Messager messager,List<Class<? extends Annotation>> permittedAnnotations,List<Class<? extends Annotation>> permittedInterStageInputAnnotations,List<Class<? extends Annotation>> delegateMethodAnnotationsThatSkipDiffModels){
  final List<MethodParamModel> methodParamModels=new ArrayList<>();
  final List<Name> savedParameterNames=getSavedParameterNames(method);
  final List<? extends VariableElement> params=method.getParameters();
  for (int i=0, size=params.size(); i < size; i++) {
    final VariableElement param=params.get(i);
    final String paramName=savedParameterNames == null ? param.getSimpleName().toString() : savedParameterNames.get(i).toString();
    try {
      final TypeSpec typeSpec=generateTypeSpec(param.asType());
      if (!typeSpec.isValid()) {
        messager.printMessage(Diagnostic.Kind.WARNING,String.format("The type of '%s' cannot be fully determined at compile time. " + "This can cause issues if the target referenced is from a different " + "package. " + "Learn more at https://fburl.com/fblitho-cross-package-error.",param.getSimpleName()),param);
      }
      methodParamModels.add(MethodParamModelFactory.create(typeSpec,paramName,getLibraryAnnotations(param,permittedAnnotations),getExternalAnnotations(param),permittedInterStageInputAnnotations,canCreateDiffModels(method,delegateMethodAnnotationsThatSkipDiffModels),param));
    }
 catch (    Exception e) {
      throw new ComponentsProcessingException(param,String.format("Error processing the param '%s'. Are your imports set up correctly? The causing error was: %s",param,e));
    }
  }
  return methodParamModels;
}
