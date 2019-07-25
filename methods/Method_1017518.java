@Override public DistributedStateMachineConfigurer<S,E> ensemble(StateMachineEnsemble<S,E> ensemble){
  this.ensemble=ensemble;
  return this;
}
