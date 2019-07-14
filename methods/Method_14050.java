@JsonProperty("error") @JsonInclude(Include.NON_NULL) public String getError(){
  if (_errorMessage != null) {
    return _errorMessage;
  }
 else   if (!isFiniteRange()) {
    return ERR_NO_NUMERIC_VALUE_PRESENT;
  }
  return null;
}
