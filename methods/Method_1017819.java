@SuppressWarnings("unchecked") @Override public Callable<V> generate(SourceOfRandomness random,GenerationStatus status){
  return makeLambda(Callable.class,componentGenerators().get(0),status);
}
