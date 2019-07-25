@Nonnull public static <M,E,F>MobiusStore<M,E,F> create(Init<M,F> init,Update<M,E,F> update,M startModel){
  return new MobiusStore<>(init,update,startModel);
}
