@JsonCreator public static Match create(@JsonProperty("condition") RequestCondition condition,@JsonProperty("features") FeatureSet features){
  return new AutoValue_Match(condition,features);
}
