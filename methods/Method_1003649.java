public static <T>Handler build(final Function<List<Handler>,? extends T> toChainBuilder,final Action<? super T> chainBuilderAction) throws Exception {
  List<Handler> handlers=Lists.newLinkedList();
  T chainBuilder=toChainBuilder.apply(handlers);
  chainBuilderAction.execute(chainBuilder);
  return Handlers.chain(handlers.toArray(new Handler[handlers.size()]));
}
