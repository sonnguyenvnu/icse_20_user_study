public LockProcessor<A,L> lockNameIs(Function<A,String[]> lockNameGetter){
  this.lockNameGetter=lockNameGetter;
  return this;
}
