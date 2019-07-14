/** 
 * Inject context into target.
 */
public void injectContext(final Object targetObject){
  final Class targetType=targetObject.getClass();
  final ScopeData scopeData=scopeDataInspector.inspectClassScopesWithCache(targetType);
  final Targets targets=new Targets(targetObject,scopeData);
  scopeResolver.forEachScope(madvocScope -> madvocScope.inject(targets));
  scopeResolver.forScope(ParamsScope.class,scope -> scope.inject(targets));
  final ServletContext servletContext=madvocController.getApplicationContext();
  if (servletContext != null) {
    scopeResolver.forEachScope(madvocScope -> madvocScope.inject(servletContext,targets));
  }
}
