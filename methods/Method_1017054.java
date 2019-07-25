static LifeCycle combined(Iterable<LifeCycle> many){
  return ManyLifeCycle.of(ImmutableList.copyOf(many));
}
