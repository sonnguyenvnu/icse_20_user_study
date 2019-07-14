@JsonCreator public static MatchResult partialMatch(@JsonProperty("distance") double distance){
  return new EagerMatchResult(distance);
}
