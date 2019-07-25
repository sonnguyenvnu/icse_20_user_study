@Override public <T>Iterable<Supplier<? extends T>> provide(TypeToken<T> type){
  ImmutableList<Provider<? extends T>> providers=GuiceUtil.allProvidersOfType(injector,type);
  return FluentIterable.from(providers.reverse()).transform(provider -> provider::get);
}
