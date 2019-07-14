@JsonProperty("step") @JsonInclude(Include.NON_NULL) public Double getStep(){
  if (getError() == null) {
    return _step;
  }
  return null;
}
