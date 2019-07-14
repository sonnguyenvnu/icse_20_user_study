/** 
 * Iterates all targets and for each target iterates all OUT injection points of given scope.
 */
public void forEachTargetAndOut(final MadvocScope scope,final BiConsumer<Target,InjectionPoint> biConsumer){
  for (  final Target target : targets) {
    final ScopeData scopeData=target.scopeData();
    if (scopeData.out() == null) {
      continue;
    }
    for (    final InjectionPoint out : scopeData.out()) {
      if (out.scope() != scope) {
        continue;
      }
      biConsumer.accept(target,out);
    }
  }
}
