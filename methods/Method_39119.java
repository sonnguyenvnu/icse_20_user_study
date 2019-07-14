/** 
 * Iterates all targets and for each target iterates all IN injection points of given scope.
 */
public void forEachTargetAndIn(final MadvocScope scope,final BiConsumer<Target,InjectionPoint> biConsumer){
  for (  final Target target : targets) {
    final ScopeData scopeData=target.scopeData();
    if (scopeData.in() == null) {
      continue;
    }
    for (    final InjectionPoint in : scopeData.in()) {
      if (in.scope() != scope) {
        continue;
      }
      biConsumer.accept(target,in);
    }
  }
}
