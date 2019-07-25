@JsonCreator public static MetricsRowKey create(@JsonProperty("series") Series series,@JsonProperty("base") Long base){
  return new MetricsRowKey(series,base);
}
