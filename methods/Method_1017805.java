@Override public Clock generate(SourceOfRandomness random,GenerationStatus status){
  return Clock.fixed(random.nextInstant(min,max),UTC_ZONE_ID);
}
