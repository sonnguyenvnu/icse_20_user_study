public RequestPatternBuilder withoutHeader(String key){
  headers.put(key,MultiValuePattern.absent());
  return this;
}
