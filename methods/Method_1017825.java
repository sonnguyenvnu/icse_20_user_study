@SuppressWarnings("unchecked") @Override public IntFunction<R> generate(SourceOfRandomness random,GenerationStatus status){
  return makeLambda(IntFunction.class,componentGenerators().get(0),status);
}
