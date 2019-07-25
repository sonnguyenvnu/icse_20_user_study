@JsonCreator public static QueryContext create(@JsonProperty("queryId") final UUID queryId,@JsonProperty("clientContext") Optional<JsonNode> clientContext,@JsonProperty("httpContext") Optional<HttpContext> httpContext){
  return new AutoValue_QueryContext(queryId,clientContext,httpContext);
}
