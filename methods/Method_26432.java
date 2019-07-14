private static Set<TimeUnit> timeUnits(List<String> wordsLists){
  return wordsLists.stream().map(UNIT_FOR_SUFFIX::get).filter(x -> x != null).collect(toImmutableSet());
}
