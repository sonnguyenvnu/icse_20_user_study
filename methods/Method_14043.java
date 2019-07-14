@JsonProperty("blankChoice") @JsonInclude(Include.NON_NULL) public OtherChoice getBlankChoice(){
  if (getError() == null && !_config.omitBlank && (_config.selectBlank || _blankCount > 0)) {
    return new OtherChoice(_config.selectBlank,_blankCount);
  }
  return null;
}
