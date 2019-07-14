@Override @JsonProperty("body") public String getBodyAsString(){
  return stringFromBytes(body,encodingFromContentTypeHeaderOrUtf8());
}
