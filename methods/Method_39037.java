/** 
 * Creates new instance of action runtime configuration. Initialize caches.
 */
public ActionRuntime createActionRuntime(final ActionHandler actionHandler,final Class actionClass,final Method actionClassMethod,final Class<? extends ActionResult> actionResult,final Class<? extends ActionResult> defaultActionResult,final ActionFilter[] filters,final ActionInterceptor[] interceptors,final ActionDefinition actionDefinition,final boolean async,final boolean auth){
  if (actionHandler != null) {
    return new ActionRuntime(actionHandler,actionClass,actionClassMethod,filters,interceptors,actionDefinition,NoneActionResult.class,NoneActionResult.class,async,auth,null,null);
  }
  final ScopeData scopeData=scopeDataInspector.inspectClassScopes(actionClass);
  final Class[] paramTypes=actionClassMethod.getParameterTypes();
  final MethodParam[] params=new MethodParam[paramTypes.length];
  final Annotation[][] paramAnns=actionClassMethod.getParameterAnnotations();
  String[] methodParamNames=null;
  for (int ndx=0; ndx < paramTypes.length; ndx++) {
    Class paramType=paramTypes[ndx];
    if (methodParamNames == null) {
      methodParamNames=actionMethodParamNameResolver.resolveParamNames(actionClassMethod);
    }
    final String paramName=methodParamNames[ndx];
    final Annotation[] parameterAnnotations=paramAnns[ndx];
    final ScopeData paramsScopeData=scopeDataInspector.inspectMethodParameterScopes(paramName,paramType,parameterAnnotations);
    MapperFunction mapperFunction=null;
    for (    final Annotation annotation : parameterAnnotations) {
      if (annotation instanceof Mapper) {
        mapperFunction=MapperFunctionInstances.get().lookup(((Mapper)annotation).value());
        break;
      }
    }
    params[ndx]=new MethodParam(paramTypes[ndx],paramName,scopeDataInspector.detectAnnotationType(parameterAnnotations),paramsScopeData,mapperFunction);
  }
  return new ActionRuntime(null,actionClass,actionClassMethod,filters,interceptors,actionDefinition,actionResult,defaultActionResult,async,auth,scopeData,params);
}
