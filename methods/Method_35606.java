public static StringValuePattern matchingJsonPath(String value){
  return new MatchesJsonPathPattern(value);
}
