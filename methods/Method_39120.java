public void forEachTargetAndIn(final BiConsumer<Target,InjectionPoint> biConsumer){
  for (  final Target target : targets) {
    final ScopeData scopeData=target.scopeData();
    if (scopeData.in() == null) {
      continue;
    }
    for (    final InjectionPoint in : scopeData.in()) {
      biConsumer.accept(target,in);
    }
  }
}
