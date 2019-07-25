@Override public LocalDate generate(SourceOfRandomness random,GenerationStatus status){
  return LocalDate.ofEpochDay(random.nextLong(min.toEpochDay(),max.toEpochDay()));
}
