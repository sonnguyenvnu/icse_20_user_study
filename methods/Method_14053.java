@JsonProperty("baseBins") @JsonInclude(Include.NON_NULL) public int[] getBaseBins(){
  if (getError() == null) {
    return _baseBins;
  }
  return null;
}
