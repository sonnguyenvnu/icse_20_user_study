public LifecycleInjector run(LifecycleListener mainClass){
  return run(ModulesEx.fromInstance(mainClass),new String[]{});
}
