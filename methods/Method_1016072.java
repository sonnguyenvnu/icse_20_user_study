@Override public void call(Injector injector){
  this.resourceMapper.setInjector(injector);
  this.preDestroyMonitor.addScopeBindings(injector.getScopeBindings());
}
