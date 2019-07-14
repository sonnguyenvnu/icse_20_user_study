@Override @JsonIgnore public String getExpected(){
  return BaseEncoding.base64().encode(expectedValue);
}
