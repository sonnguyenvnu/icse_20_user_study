@Override public T generate(SourceOfRandomness random,GenerationStatus status){
  return makeLambda(lambdaType,returnValueGenerator,status);
}
