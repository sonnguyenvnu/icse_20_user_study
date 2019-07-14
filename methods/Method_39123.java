/** 
 * Joins action and parameters into one single array of Targets.
 */
protected Target[] makeTargets(final Target actionTarget,final MethodParam[] methodParams){
  if (methodParams == null) {
    return new Target[]{actionTarget};
  }
  final Target[] target=new Target[methodParams.length + 1];
  target[0]=actionTarget;
  final Object action=actionTarget.value();
  for (int i=0; i < methodParams.length; i++) {
    final MethodParam methodParam=methodParams[i];
    final Class paramType=methodParam.type();
    final Target paramTarget;
    if (methodParam.annotationType() == null) {
      final ScopeData newScopeData=methodParam.scopeData().inspector().inspectClassScopesWithCache(paramType);
      paramTarget=Target.ofValue(createActionMethodArgument(paramType,action),newScopeData);
    }
 else     if (methodParam.annotationType() == Out.class) {
      paramTarget=Target.ofMethodParam(methodParam,createActionMethodArgument(paramType,action));
    }
 else {
      paramTarget=Target.ofMethodParam(methodParam,type -> createActionMethodArgument(type,action));
    }
    target[i + 1]=paramTarget;
  }
  return target;
}
