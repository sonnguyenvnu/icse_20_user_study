@SuppressWarnings("unchecked") @Override public LongFunction<R> generate(SourceOfRandomness random,GenerationStatus status){
  return makeLambda(LongFunction.class,componentGenerators().get(0),status);
}
