@Override public ZoneId generate(SourceOfRandomness random,GenerationStatus status){
  return ZoneId.of(random.choose(getAvailableZoneIds()));
}
