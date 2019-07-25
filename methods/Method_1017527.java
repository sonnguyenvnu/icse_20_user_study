@Override public JoinTransitionConfigurer<S,E> source(S source){
  this.sources.add(source);
  return this;
}
