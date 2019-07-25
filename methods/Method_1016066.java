List<Module> build(Injector injector) throws Exception {
  for (  ModuleProvider provider : providers) {
    provider.getInstance(injector);
  }
  return resolvedModules;
}
