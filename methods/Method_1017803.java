@Override public Charset generate(SourceOfRandomness random,GenerationStatus status){
  return Charset.forName(random.choose(availableCharsets().keySet()));
}
