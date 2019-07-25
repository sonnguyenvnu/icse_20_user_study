@SuppressWarnings("unchecked") @Override public Predicate<T> generate(SourceOfRandomness random,GenerationStatus status){
  return makeLambda(Predicate.class,gen().type(boolean.class),status);
}
