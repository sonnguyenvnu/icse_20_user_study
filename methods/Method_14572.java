@JsonProperty("prependColumnName") @JsonInclude(Include.NON_NULL) public Boolean getPrependColumnName(){
  return _combinedColumnName == null ? null : _prependColumnName;
}
