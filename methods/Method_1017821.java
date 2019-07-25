@SuppressWarnings("unchecked") @Override public BiFunction<T,U,R> generate(SourceOfRandomness random,GenerationStatus status){
  return makeLambda(BiFunction.class,componentGenerators().get(2),status);
}
