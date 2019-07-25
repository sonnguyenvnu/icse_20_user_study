@SuppressWarnings("unchecked") @Override public Function<F,T> generate(SourceOfRandomness random,GenerationStatus status){
  return makeLambda(Function.class,componentGenerators().get(1),status);
}
