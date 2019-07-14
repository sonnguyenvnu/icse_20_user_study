@JsonProperty(FROM) @JsonInclude(Include.NON_NULL) public Double getFrom(){
  if (getError() == null) {
    return _config._from;
  }
  return null;
}
