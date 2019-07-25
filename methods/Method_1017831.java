@Override public Locale generate(SourceOfRandomness random,GenerationStatus status){
  return random.choose(getAvailableLocales());
}
