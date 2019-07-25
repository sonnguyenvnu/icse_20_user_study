@JsonCreator public static All create(@JsonProperty("conditions") List<RequestCondition> conditions){
  return new AutoValue_All(conditions);
}
