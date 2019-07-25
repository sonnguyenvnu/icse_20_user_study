@Override public Boolean generate(SourceOfRandomness random,GenerationStatus status){
  return turnOffRandomness == null ? random.nextBoolean() : status.attempts() % 2 != 0;
}
