@SuppressWarnings("unchecked") @Override public UnaryOperator<T> generate(SourceOfRandomness random,GenerationStatus status){
  return makeLambda(UnaryOperator.class,componentGenerators().get(0),status);
}
