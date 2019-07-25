@Override public Optional<Listener> acquire(ContextT context){
  int currentInFlight=getInflight();
  inflightDistribution.addSample(currentInFlight);
  if (currentInFlight >= getLimit()) {
    return createRejectedListener();
  }
  return Optional.of(createListener());
}
