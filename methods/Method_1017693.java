@Override public T generate(SourceOfRandomness random,GenerationStatus status){
  return instantiate(ctor,arguments(random,status));
}
