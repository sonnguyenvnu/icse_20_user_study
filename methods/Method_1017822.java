@SuppressWarnings("unchecked") @Override public BinaryOperator<T> generate(SourceOfRandomness random,GenerationStatus status){
  return makeLambda(BinaryOperator.class,componentGenerators().get(0),status);
}
