@JsonCreator public static FullQuery create(@JsonProperty("trace") QueryTrace trace,@JsonProperty("errors") List<RequestError> errors,@JsonProperty("groups") List<ResultGroup> groups,@JsonProperty("statistics") Statistics statistics,@JsonProperty("limits") ResultLimits limits,@JsonProperty("dataDensity") Optional<Histogram> dataDensity){
  return new AutoValue_FullQuery(trace,errors,groups,statistics,limits,dataDensity);
}
