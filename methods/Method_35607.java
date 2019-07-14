public static StringValuePattern matchingJsonPath(String value,StringValuePattern valuePattern){
  return new MatchesJsonPathPattern(value,valuePattern);
}
