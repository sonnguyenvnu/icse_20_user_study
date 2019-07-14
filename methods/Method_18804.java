private static List<MethodParamModel> getParams(SpecMethodModel<UpdateStateMethod,Void> updateStateMethodModel){
  final List<MethodParamModel> params=new ArrayList<>();
  for (  MethodParamModel methodParamModel : updateStateMethodModel.methodParams) {
    for (    Annotation annotation : methodParamModel.getAnnotations()) {
      if (annotation.annotationType().equals(Param.class)) {
        params.add(methodParamModel);
        break;
      }
    }
  }
  return params;
}
