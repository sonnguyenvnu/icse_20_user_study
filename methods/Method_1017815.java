@Override public ZoneOffset generate(SourceOfRandomness random,GenerationStatus status){
  int minSeconds=min.getTotalSeconds();
  int maxSeconds=max.getTotalSeconds();
  return ZoneOffset.ofTotalSeconds((minSeconds <= maxSeconds) ? random.nextInt(minSeconds,maxSeconds) : random.nextInt(maxSeconds,minSeconds));
}
