private static Registry registry(Action<? super BindingsSpec> bindings,Registry baseRegistry,Function<Module,Injector> injectorFactory) throws Exception {
  Injector injector=buildInjector(baseRegistry,bindings,injectorFactory);
  return registry(injector);
}
