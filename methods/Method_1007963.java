@JsonCreator static RootFs create(@JsonProperty("Type") final String type,@JsonProperty("Layers") final List<String> layers){
  return new AutoValue_RootFs(type,layers);
}
