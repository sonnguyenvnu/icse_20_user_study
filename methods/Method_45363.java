public MocoEventAction createTrigger(){
  if (!async && latency != null) {
    throw new IllegalArgumentException("Latency only works for async mode");
  }
  MocoEventAction action=doCreateAction();
  if (this.async) {
    return Moco.async(action,latency.asProcedure());
  }
  return action;
}
