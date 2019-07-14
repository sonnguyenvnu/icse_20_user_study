@JsonProperty(FROM_X) @JsonInclude(Include.NON_NULL) public Double getFromX(){
  if (errorMessage_x == null && !Double.isInfinite(min_x) && !Double.isInfinite(max_x)) {
    return config.from_x;
  }
  return null;
}
