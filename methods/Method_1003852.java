@Override public void execute(Chain chain) throws Exception {
  List<Action<Chain>> delegates=new ArrayList<>(this.delegates);
  for (  RatpackServerCustomizer customizer : customizers) {
    delegates.addAll(customizer.getHandlers());
  }
  if (handlers.size() == 1 || delegates.isEmpty()) {
    delegates.add(singleHandlerAction());
  }
  delegates.add(staticResourcesAction(chain.getServerConfig()));
  AnnotationAwareOrderComparator.sort(delegates);
  for (  Action<Chain> delegate : delegates) {
    if (!(delegate instanceof ChainConfigurers)) {
      delegate.execute(chain);
    }
  }
}
