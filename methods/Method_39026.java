protected ActionInterceptor[] parseActionInterceptors(final Class<?> actionClass,final Method actionMethod,final ActionConfig actionConfig){
  Class<? extends ActionInterceptor>[] interceptorClasses=readActionInterceptors(actionMethod);
  if (interceptorClasses == null) {
    interceptorClasses=readActionInterceptors(actionClass);
  }
  if (interceptorClasses == null) {
    interceptorClasses=actionConfig.getInterceptors();
  }
  return interceptorsManager.resolveAll(interceptorClasses);
}
