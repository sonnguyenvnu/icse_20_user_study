private static <T extends WrappedModule,U extends Module>List<T> map(final List<U> in,Function<U,T> factory,final Collection<WrappedModule<Module,ModuleHandler>> coll){
  return Collections.unmodifiableList((List<? extends T>)in.stream().map(module -> {
    final T impl=factory.apply(module);
    coll.add(impl);
    return impl;
  }
).collect(Collectors.toList()));
}
