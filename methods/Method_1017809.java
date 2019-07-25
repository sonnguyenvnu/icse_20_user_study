@Override public MonthDay generate(SourceOfRandomness random,GenerationStatus status){
  long minEpochDay=min.atYear(2000).toEpochDay();
  long maxEpochDay=max.atYear(2000).toEpochDay();
  LocalDate date=LocalDate.ofEpochDay(random.nextLong(minEpochDay,maxEpochDay));
  return MonthDay.of(date.getMonthValue(),date.getDayOfMonth());
}
