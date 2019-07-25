default <T>BindingsSpec provider(Class<T> publicType,Provider<? extends T> provider){
  return provider(TypeLiteral.get(publicType),provider);
}
