@JsonProperty("exceptions") @JsonInclude(Include.NON_NULL) public List<ExceptionMessage> getJsonExceptions(){
  if (_latestExceptions != null) {
    return _latestExceptions.stream().map(e -> new ExceptionMessage(e)).collect(Collectors.toList());
  }
  return null;
}
