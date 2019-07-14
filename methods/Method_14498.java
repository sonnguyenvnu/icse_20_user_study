@JsonProperty("keyCellIndex") @JsonInclude(Include.NON_NULL) public Integer getJsonKeyCellIndex(){
  if (columns.size() > 0) {
    return getKeyColumnIndex();
  }
  return null;
}
