@Override public void call(Injector injector){
  LOG.info("Bindings for " + label);
  LOG.info(describeBindings("Binding  : ",injector.getBindings().entrySet()));
  Map<Key<?>,Binding<?>> jitBindings=Maps.difference(injector.getAllBindings(),injector.getBindings()).entriesOnlyOnLeft();
  LOG.info(describeBindings("JIT      : ",jitBindings.entrySet()));
}
