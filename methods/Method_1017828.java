@SuppressWarnings("unchecked") @Override public Supplier<T> generate(SourceOfRandomness random,GenerationStatus status){
  return makeLambda(Supplier.class,componentGenerators().get(0),status);
}
