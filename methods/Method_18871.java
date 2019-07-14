private static boolean hasPermittedAnnotation(MethodParamModel methodParam){
  return MethodParamModelUtils.isAnnotatedWith(methodParam,FromEvent.class) || MethodParamModelUtils.isAnnotatedWith(methodParam,Prop.class) || MethodParamModelUtils.isAnnotatedWith(methodParam,InjectProp.class) || MethodParamModelUtils.isAnnotatedWith(methodParam,TreeProp.class) || MethodParamModelUtils.isAnnotatedWith(methodParam,CachedValue.class) || MethodParamModelUtils.isAnnotatedWith(methodParam,State.class) || MethodParamModelUtils.isAnnotatedWith(methodParam,Param.class);
}
