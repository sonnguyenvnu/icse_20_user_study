protected void finalizeSetup(Options options){
  if (!options.jettySettings().getStopTimeout().isPresent()) {
    jettyServer.setStopTimeout(0);
  }
}
