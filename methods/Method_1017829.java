@SuppressWarnings("unchecked") @Override public ToIntBiFunction<T,U> generate(SourceOfRandomness random,GenerationStatus status){
  return makeLambda(ToIntBiFunction.class,gen().type(int.class),status);
}
