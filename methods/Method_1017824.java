@SuppressWarnings("unchecked") @Override public Function<T,R> generate(SourceOfRandomness random,GenerationStatus status){
  return makeLambda(Function.class,componentGenerators().get(1),status);
}
