@JsonCreator public static StartsWithFilter create(@JsonProperty("tag") String tag,@JsonProperty("value") String value){
  return new AutoValue_StartsWithFilter(tag,value);
}
