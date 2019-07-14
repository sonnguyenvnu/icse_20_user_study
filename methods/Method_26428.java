@VisibleForTesting static TimeUnit unitSuggestedByName(String name){
  if (name.equals("second") || name.equals("getSecond")) {
    return null;
  }
  if (name.equals("secondsPart")) {
    return NANOSECONDS;
  }
  if (name.equals("msToS")) {
    return SECONDS;
  }
  List<String> words=fixUnitCamelCase(splitToLowercaseTerms(name));
  if (words.get(0).equals("second")) {
    return null;
  }
  if (hasNameOfFromUnits(words)) {
    return null;
  }
  if (isNamedForNumberOfUnits(words)) {
    return null;
  }
  Set<TimeUnit> units=timeUnits(words);
  return units.size() == 1 ? getOnlyElement(units) : null;
}
