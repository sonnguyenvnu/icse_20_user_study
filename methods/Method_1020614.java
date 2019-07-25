synchronized void init(){
  if (initialised) {
    throw new IllegalStateException("already initialised");
  }
  First<M,F> first=store.init();
  dispatchModel(first.model());
  dispatchEffects(first.effects());
  initialised=true;
  for (  E event : eventsReceivedBeforeInit) {
    update(event);
  }
}
