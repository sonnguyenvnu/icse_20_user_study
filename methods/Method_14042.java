@JsonProperty("choiceCount") @JsonInclude(Include.NON_NULL) public Integer getChoiceCount(){
  if (_errorMessage == null && _choices.size() > getLimit()) {
    return _choices.size();
  }
  return null;
}
