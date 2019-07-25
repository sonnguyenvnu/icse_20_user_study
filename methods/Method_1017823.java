@SuppressWarnings("unchecked") @Override public DoubleFunction<R> generate(SourceOfRandomness random,GenerationStatus status){
  return makeLambda(DoubleFunction.class,componentGenerators().get(0),status);
}
