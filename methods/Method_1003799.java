public static <T>void search(Injector injector,TypeToken<T> type,Function<Provider<? extends T>,Boolean> transformer){
  ConcurrentMap<TypeToken<?>,Boolean> cache=TypeCaching.cache(type);
  Map<Key<?>,Binding<?>> bindings=injector.getBindings();
  for (  Map.Entry<Key<?>,Binding<?>> keyBindingEntry : bindings.entrySet()) {
    final Key<?> key=keyBindingEntry.getKey();
    final Binding<?> binding=keyBindingEntry.getValue();
    TypeLiteral<?> bindingType=key.getTypeLiteral();
    if (TypeCaching.isAssignableFrom(cache,type,toTypeToken(bindingType))) {
      @SuppressWarnings("unchecked") Provider<? extends T> provider=(Provider<? extends T>)binding.getProvider();
      try {
        if (!transformer.apply(provider)) {
          return;
        }
      }
 catch (      Exception e) {
        throw uncheck(e);
      }
    }
  }
  Injector parent=injector.getParent();
  if (parent != null) {
    search(parent,type,transformer);
  }
}
