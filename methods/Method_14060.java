@JsonProperty(TO_X) @JsonInclude(Include.NON_NULL) public Double getToX(){
  if (errorMessage_x == null && !Double.isInfinite(min_x) && !Double.isInfinite(max_x)) {
    return config.to_x;
  }
  return null;
}
